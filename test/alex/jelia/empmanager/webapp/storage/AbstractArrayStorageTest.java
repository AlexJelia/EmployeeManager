package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.exception.StorageException;
import alex.jelia.empmanager.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Name" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("TestOverflow"));
    }
}