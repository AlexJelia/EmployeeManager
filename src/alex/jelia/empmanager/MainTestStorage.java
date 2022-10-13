package alex.jelia.empmanager;

import alex.jelia.empmanager.webapp.exception.NotExistStorageException;
import alex.jelia.empmanager.webapp.model.*;
import alex.jelia.empmanager.webapp.storage.*;

import java.time.Month;

public class MainTestStorage {
    private final static Storage STORAGE = new MapUuidStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1","Alex");
        final Resume r2 = new Resume("uuid2","David");
        final Resume r3 = new Resume("uuid3","Boris");
        final Resume r4 = new Resume("uuid4","Frank");
        final Resume r5 = new Resume("uuid5","Frank");

        STORAGE.save(r1);
        STORAGE.save(r3);
        STORAGE.save(r5);
        STORAGE.save(r2);
        STORAGE.save(r4);

        printAll();
        System.out.println("Size: " + STORAGE.size());

        try {
            System.out.println("Get dummy: " + STORAGE.get("dummy"));
        } catch (NotExistStorageException e) {
            System.out.println("netu dummy");
        }

        r1.addContact(ContactType.MAIL, "mail1@ya.ru");
        r1.addContact(ContactType.PHONE, "11111");
        r1.addSection(SectionType.OBJECTIVE, new SimpleTextSection("Objective1"));
        r1.addSection(SectionType.PERSONAL, new SimpleTextSection("Personal data"));
        r1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        r1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        r1.addSection(SectionType.EXPERIENCE,
                new OrganizationsSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        r1.addSection(SectionType.EDUCATION,
                new OrganizationsSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));

        System.out.println(r1);
        System.out.println(r1.getContact(ContactType.MAIL));
        System.out.println(r1.getSection(SectionType.OBJECTIVE));
        r1.addContact(ContactType.MAIL, "huhu.ru");
        System.out.println(r1.getContact(ContactType.MAIL));
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}