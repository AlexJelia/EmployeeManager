package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        int numsMoved = size - index - 1;
        if (numsMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numsMoved);
        }
    }

    @Override
    protected void insertElement(Resume r, int index) {

//      https://javarush.ru/groups/posts/3930-kofe-breyk-156-kak-ispoljhzovatjh-metod-arraysbinarysearch-v-java
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
