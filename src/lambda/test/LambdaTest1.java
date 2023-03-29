package lambda.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class LambdaTest1 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("tom", 8));
        users.add(new User("mike", 9));
        users.add(new User("jack", 11));
        users.add(new User("alice", 15));

        AtomicReference<Integer> bigMan = new AtomicReference<>(0);
        users.forEach(user -> {
            if(user.getAge() > 10) {
                bigMan.getAndSet(bigMan.get() + 1);
            }
        });
        System.out.println(bigMan.get());
    }
}
