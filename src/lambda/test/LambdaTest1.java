package lambda.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class LambdaTest1 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("tom", 9));
        users.add(new User("mike", 9));
        users.add(new User("alice", 11));
        users.add(new User("alice", 11));

        Map<Integer, Long> userInfo = users.stream().
                collect(Collectors.groupingBy(User::getAge, Collectors.counting()));

        System.out.println("11岁的有" + userInfo.get(11) + "位");


    }
}
