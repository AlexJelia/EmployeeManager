package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("Resume " + uuid + " is not in storage");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
