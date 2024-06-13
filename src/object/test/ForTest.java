package object.test;

import java.util.List;

public class ForTest {
    public static void main(String[] args) {
        List<ForTest_User> forTestUsers = List.of(
                new ForTest_User("你好"),
                new ForTest_User("吃了吗")
        );
        for (ForTest_User item : forTestUsers) {
            if ("吃了吗".equals(item.getName())) {
                item.setName("滚蛋");
            }
        }
        forTestUsers.forEach(item -> System.out.println(item.toString()));

        ForTest_User user = new ForTest_User("张三");
        user.setSbName(user);
        System.out.println(user.getName());
    }
}

class ForTest_User {
    private String name;

    public ForTest_User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForTest_User setSbName(ForTest_User user) {
        user.setName("傻逼名字");
        return user;
    }

    @Override
    public String toString() {
        return "ForTest_User{" +
                "name='" + name + '\'' +
                '}';
    }
}
