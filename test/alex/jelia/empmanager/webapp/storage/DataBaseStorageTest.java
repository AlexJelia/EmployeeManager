package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.Config;

public class DataBaseStorageTest extends AbstractStorageTest {
    public DataBaseStorageTest() {
        super(new DataBaseStorage(Config.get().getUrl(),Config.get().getUser(),Config.get().getPassword()));
    }
}
