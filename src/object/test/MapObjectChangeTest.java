package object.test;

import java.util.HashMap;
import java.util.Map;

public class MapObjectChangeTest {
    public static void main(String[] args) {
        User user1 = new User("1", "张三");
        User user2 = new User("2", "李四");
        Map<String, User> map = new HashMap<>();
        map.put("a", user1);
        map.put("b", user2);

        map.get("a").setName("张三个");
        System.out.println(map.get("a"));
    }
}

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
