package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(RESOURCES_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}