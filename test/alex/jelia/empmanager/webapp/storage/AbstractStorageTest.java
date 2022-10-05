package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.exception.ExistStorageException;
import alex.jelia.empmanager.webapp.exception.NotExistStorageException;
import alex.jelia.empmanager.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private final static Resume RESUME_TEST = new Resume("NameTest");
    private static final String NAME_1 = "Name1";
    private final static Resume RESUME_1 = new Resume("uuid1",NAME_1);


    private static final String NAME_2 = "Name2";
    private final static Resume RESUME_2 = new Resume(NAME_2);

    private static final String NAME_3 = "Name3";
    private final static Resume RESUME_3 = new Resume(NAME_3);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before                    //выполняется перед каждым тестом
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume("uuid1",NAME_1);
        storage.update(newResume);
        assertTrue(newResume == storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() {
        storage.save(RESUME_TEST);
        assertSize(4);
        assertGet(RESUME_TEST);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("uuid12");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        assertSize(2);
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = storage.getAllSorted();
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void size() {
        assertSize(3);
    }


    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}