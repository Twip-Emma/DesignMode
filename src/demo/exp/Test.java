package demo.exp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        // 创建一个Map对象并添加一些数据
        Map<String, Integer> dataMap = new HashMap<>();
        dataMap.put("key1", 123);
        dataMap.put("key2", 456);
        dataMap.put("key3", 789);

        // 文件路径
        String filePath = "data/test.data";

        // 将Map对象持久化保存到文件
        saveMapToFile(dataMap, filePath);

        // 从文件中读取持久化的Map对象
        Map<String, Integer> loadedMap = loadMapFromFile(filePath);

        // 打印加载的Map对象
        System.out.println("Loaded Map from file:");
        for (Map.Entry<String, Integer> entry : loadedMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // 将Map对象保存到文件
    private static void saveMapToFile(Map<String, Integer> map, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(map);
            System.out.println("Map saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从文件中加载Map对象
    private static Map<String, Integer> loadMapFromFile(String filePath) {
        Map<String, Integer> map = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            map = (Map<String, Integer>) ois.readObject();
            System.out.println("Map loaded successfully from " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }
}
