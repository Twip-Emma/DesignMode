package object.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClazzTest {
    public static void main(String[] args) {
        String str = "12312312312";
        String regex = "^\\d{11}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());

//        System.out.println(isListRepeat(List.of("1","2",3,"3",5,"5")));

//        System.out.println(Boolean.FALSE.equals(false));
//        System.out.println(Boolean.FALSE.equals(null));
//        System.out.println(Boolean.FALSE.equals(true));
//        System.out.println(Objects.equals(false, null));
//        System.out.println(Objects.equals(false, false));
//        System.out.println(Objects.equals(false, true));
    }

    public static <T> boolean isListRepeat(List<T> list) {
        Set<T> set = new HashSet<>(list);
        return list.size() != set.size();
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
