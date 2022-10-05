package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();
    void update(Resume r);
    void save(Resume r);
    Resume get(String uuid);
    void delete(String uuid);

    //Sorted by name
    List<Resume> getAllSorted();
    int size();

}
