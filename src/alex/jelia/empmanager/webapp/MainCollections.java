package alex.jelia.empmanager.webapp;

import alex.jelia.empmanager.webapp.model.Resume;

import java.util.*;

public class MainCollections {

    private static final String UUID_1 = "uuid1";
    private final static Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private final static Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private final static Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private final static Resume RESUME_4 = new Resume(UUID_4);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);


        for (Resume r : collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                //       collection.remove(r);
            }
        }

        System.out.println(collection);
        System.out.println("            ");

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (Objects.equals(resume.getUuid(), UUID_1)) {
                iterator.remove();
            } else {
                System.out.println(resume);
            }
        }

        System.out.println("    Map        ");

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        //Неоптимально,лезть в мапу - плохая идея,к тому же это медленно
        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }
        System.out.println("второй вариант");

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());

        }
    }
}
