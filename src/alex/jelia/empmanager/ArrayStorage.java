package alex.jelia.empmanager;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    static int fillCell = 0;
    static int size = 0;

    void clear() {

        Arrays.fill(storage,0,size,null);

    }

    void save(Resume r) {
        storage[fillCell] = r;
        fillCell++;
        size++;
    }

    Resume get(String uuid) {
        for(int i = 0;i<size;i++) {   // if <=size throws null pointer ex
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
               return null;
    }

    void delete(String uuid) {

        for(int i = 0;i<size;i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage,size);
    }

    int size() {
        return size;
    }
}