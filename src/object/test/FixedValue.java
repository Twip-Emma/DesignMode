package object.test;

import java.util.concurrent.ConcurrentHashMap;

public class FixedValue {
    public static final ConcurrentHashMap<Integer, String> userPower = new ConcurrentHashMap<>();

    static {
        userPower.put(0, "普通用户");
        userPower.put(1, "管理员");
        userPower.put(2, "超级管理员");
    }
}
