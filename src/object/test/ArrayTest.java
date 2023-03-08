package object.test;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest {
    /**
     * 【强制】使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入的是类型完全一
     * 致、长度为 0 的空数组。
     * 反例：直接使用 toArray 无参方法存在问题，此方法返回值只能是 Object[]类，若强转其它类型数组将出现
     * ClassCastException 错误。
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        String[] array = list.toArray(new String[0]);
        for (String s: array){
            System.out.println(s);
        }
    }
}
