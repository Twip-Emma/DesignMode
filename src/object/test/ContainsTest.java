package object.test;

import java.util.concurrent.ConcurrentHashMap;

public class ContainsTest {
    public static void main(String[] args) {
        int i = 1;
        ConcurrentHashMap<Integer, String> power = FixedValue.userPower;

        if (power.containsKey(4)) {
            System.out.println("存在");
        } else {
            System.out.println("不存在");
        }
    }
}
