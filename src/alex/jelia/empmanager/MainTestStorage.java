package alex.jelia.empmanager;

import alex.jelia.empmanager.webapp.exception.NotExistStorageException;
import alex.jelia.empmanager.webapp.model.Resume;
import alex.jelia.empmanager.webapp.storage.*;

public class MainTestStorage {
    private final static Storage SOME_STORAGE = new MapUuidStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1","Alex");
        final Resume r2 = new Resume("uuid2","David");
        final Resume r3 = new Resume("uuid3","Boris");
        final Resume r4 = new Resume("uuid4","Frank");
        final Resume r5 = new Resume("uuid5","Frank");

        SOME_STORAGE.save(r1);
        SOME_STORAGE.save(r3);
        SOME_STORAGE.save(r5);
        SOME_STORAGE.save(r2);
        SOME_STORAGE.save(r4);

        System.out.println("Get r1: " + SOME_STORAGE.get(r1.getUuid()));
        printAll();
        System.out.println("Size: " + SOME_STORAGE.size());

        try {
            System.out.println("Get dummy: " + SOME_STORAGE.get("dummy"));
        } catch (NotExistStorageException e) {
            System.out.println("netu dummy");
        }

        SOME_STORAGE.delete(r1.getUuid());
        System.out.println("Delete " + r1.getUuid());
        printAll();
        System.out.println("Size: " + SOME_STORAGE.size());

        SOME_STORAGE.clear();
        printAll();
        System.out.println("Size: " + SOME_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SOME_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}