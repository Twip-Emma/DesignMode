import java.util.HashMap;
import java.util.Map;

class User {
    private String name;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
public class TestTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("A", "25");
        map.put("B", "25");
        map.put("C", "25");
        map.put("D", null);

        System.out.println(map.get("D"));
        System.out.println(String.valueOf(map.get("D")));
        System.out.println((String.valueOf(map.get("D"))).getClass());

        String a = String.valueOf(map.get("D"));
        System.out.println(a);
        System.out.println(a.getClass());

//        User user = new User("mike", "25");
//        System.out.println(user.getName());
//        System.out.println("======");
//        System.out.println((user.getName().getClass()));
//        System.out.println("======");
//        System.out.println("======");

    }
}
