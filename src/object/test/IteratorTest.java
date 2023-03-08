package object.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    /**
     * 【强制】不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素请使用 Iterator
     * 方式，如果并发操作，需要对 Iterator 对象加锁。
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("2".equals(item)) {
                iterator.remove();
            }
        }
        for (String s: list){
            System.out.println(s);
        }
    }
}
