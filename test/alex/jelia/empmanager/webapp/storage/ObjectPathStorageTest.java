package alex.jelia.empmanager.webapp.storage;


import alex.jelia.empmanager.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {

    public ObjectPathStorageTest() {
        super(new PathStorage(RESOURCES_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}