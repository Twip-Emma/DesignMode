package object.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClazzTest {
    public static void main(String[] args) {
        ClazzTest_Teacher clazzTestTeacher = new ClazzTest_Teacher();
        clazzTestTeacher.setAge("25");
        Class<?> aClass = clazzTestTeacher.getClass();

        ArrayList<Field> targetFields = new ArrayList<>(List.of(aClass.getDeclaredFields()));
        for (Class<?> clazz = aClass.getSuperclass(); !clazz.equals(Object.class); clazz = clazz.getSuperclass()) {
            targetFields.addAll(List.of(clazz.getDeclaredFields()));
        }

        for (Field field : targetFields) {
            System.out.println(field.getName());
        }
    }
}

class ClazzTest_Teacher extends ClazzTest_User {
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

class ClazzTest_User {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
