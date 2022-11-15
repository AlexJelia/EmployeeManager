package alex.jelia.empmanager.webapp.storage;
import alex.jelia.empmanager.webapp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(RESOURCES_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}