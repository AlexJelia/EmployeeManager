package alex.jelia.empmanager.webapp.storage;


import alex.jelia.empmanager.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectFileStorageTest extends AbstractStorageTest {

    public ObjectFileStorageTest() {
        super(new FileStorage(RESOURCES_DIR, new ObjectStreamSerializer()));
    }
}