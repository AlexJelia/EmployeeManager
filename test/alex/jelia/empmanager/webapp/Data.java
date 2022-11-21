package alex.jelia.empmanager.webapp;

import alex.jelia.empmanager.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class Data {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Alex Mercer");
        R2 = new Resume(UUID_2, "John Miller");
        R3 = new Resume(UUID_3, "Peter Parker");
        R4 = new Resume(UUID_4, "Andy Bernard");

        R1.setContact(ContactType.MAIL, "a.mercer@topmail.com");
        R1.setContact(ContactType.PHONE, "88005553555");

        R2.setContact(ContactType.MAIL, "miller@topmail.com");
        R2.setContact(ContactType.PHONE, "+483955485");
        R2.setContact(ContactType.SKYPE, "miller john");

        R4.setContact(ContactType.PHONE, "+483927485");
        R4.setContact(ContactType.SKYPE, "Tunchik");

        R1.setSection(SectionType.OBJECTIVE, new SimpleTextSection("Objective1"));
        R1.setSection(SectionType.PERSONAL, new SimpleTextSection("Personal data"));
        R1.setSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationsSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.setSection(SectionType.EDUCATION,
                new OrganizationsSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationsSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }
}

