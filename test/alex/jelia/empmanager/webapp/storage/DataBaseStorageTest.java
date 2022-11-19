package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.Config;

public class DataBaseStorageTest extends AbstractStorageTest {
    public DataBaseStorageTest() {
        super(Config.get().getStorage());
    }
}
