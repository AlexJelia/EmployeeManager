package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private int size = 0;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage,0,size,null);
        size = 0;
    }

    public void update(Resume r){
        int index = getIndex(r.getUuid());
        if(index == -1){
            System.out.println("Resume " + r.getUuid() + " does not exist");
        }else{
            storage[index] = r;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if(index != -1){
            System.out.println("Resume " + r.getUuid() + " already exist");
        }else if(STORAGE_LIMIT == size){
            System.out.println("Storage overflowed");
        }else if(r.getUuid() != null){
            storage[size] = r;
            size++;
        }else{
            System.out.println("Resume " + r.getUuid() + " does not exist");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if(index == -1) {
            System.out.println("Resume " + uuid + " is not in storage");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if( index == -1) {
            System.out.println("Resume " + uuid + " is not in storage");
        }else{
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage,size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid){
        for(int i = 0;i<size;i++){
            if(uuid == storage[i].getUuid()){
                return i;
            }
        }
        return -1;
    }
}