package alex.jelia.empmanager.webapp.storage;
import alex.jelia.empmanager.webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(RESOURCES_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}