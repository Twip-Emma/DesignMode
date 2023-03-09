package object.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapIteratorTest {
    /**
     * .【推荐】使用 entrySet 遍历 Map 类集合 KV，而不是 keySet 方式进行遍历。
     * 说明：keySet 其实是遍历了 2 次，一次是转为 Iterator 对象，另一次是从 hashMap 中取出 key 所对应的
     * value。而 entrySet 只是遍历了一次就把 key 和 value 都放到了 entry 中，效率更高。如果是 JDK8，使用
     * Map.forEach 方法。
     * 正例：values()返回的是 V 值集合，是一个 list 集合对象；keySet()返回的是 K 值集合，是一个 Set 集合对
     * 象；entrySet()返回的是 K-V 值组合集合。
     */
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }
    }
}
