package object.test;

import java.util.HashMap;
import java.util.Map;

public class SetTest {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>(16);
        if(map.isEmpty()) {
            System.out.println("no element in this map.");
        }
    }
}
