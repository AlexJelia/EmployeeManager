package alex.jelia.empmanager.webapp.storage;

import alex.jelia.empmanager.webapp.model.Resume;

import java.util.Arrays;


public class ArrayStorage extends AbstractArrayStorage {
    protected void fillDeletedElement(int index){
          storage[index] = storage[size -1];
    }

    protected void insertElement(Resume r,int index){
             storage[size] = r;
    }
    protected Integer getSearchKey(String uuid){
        for(int i = 0;i<size;i++){
            if(uuid.equals(storage[i].getUuid())){
                return i;
            }
        }
        return -1;
    }

}