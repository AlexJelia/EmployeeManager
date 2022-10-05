package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.exception.ExistStorageException;
import alex.jelia.empmanager.webapp.exception.NotExistStorageException;
import alex.jelia.empmanager.webapp.model.Resume;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> doCopyAll();

    public void update(Resume r) {
        Object searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        Object searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    protected Object getExistedSearchKey(String uuid) {

        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted(){
        List<Resume> list = doCopyAll();
        Comparator<Resume> comparator = (a,b) -> {
            int comp = a.getFullName().compareTo(b.getFullName());
            return comp !=0 ? comp : a.getUuid().compareTo(b.getUuid());
        } ;
        list.sort(comparator);
        return list;
    }
}
