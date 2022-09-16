package alex.jelia.empmanager.webapp;

import alex.jelia.empmanager.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);



        /*Method[] resumeMethod = r.getClass().getDeclaredMethods();
        for (Method method : resumeMethod) {
            if (method.getName().equals("toString")) {
                System.out.println(method.invoke(r));
            }
        }*/

        Method resumeMethod = r.getClass().getMethod("toString");
        System.out.println("Method toString invoked " + resumeMethod.invoke(r));



    }
}
