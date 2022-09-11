package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + "does not exist");
        } else {
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else if(r.getUuid() != null) {
            insertElement(r,index);
            size++;
        }else{
            System.out.println("Resume " + r.getUuid() + " does not exist");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index < 0) {
            System.out.println("Resume " + uuid + " is not in storage");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }


    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
    public int size() {
        return size;
    }
    protected abstract void fillDeletedElement(int index);
    protected abstract void insertElement(Resume r,int index);
    protected abstract int getIndex(String uuid);
}
