package object.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ListTest {
    /**
     *【强制】在使用 java.util.stream.Collectors 类的 toMap()方法转为 Map 集合时，一定要使
     *用含有参数类型为 BinaryOperator，参数名为 mergeFunction 的方法，否则当出现相同 key
     *值时会抛出 IllegalStateException 异常。
     *说明：参数 mergeFunction 的作用是当出现 key 重复时，自定义对 value 的处理策略。
     */
    public static void main(String[] args) {
        List<Pair<String, Double>> pairArrayList = new ArrayList<>(3);
        pairArrayList.add(new Pair<>("version", 12.10));
        pairArrayList.add(new Pair<>("version", 12.19));
        pairArrayList.add(new Pair<>("version", 6.28));
        Map<String, Double> map = pairArrayList.stream().collect(
            // 生成的 map 集合中只有一个键值对：{version=6.28}
            Collectors.toMap(Pair::getKey, Pair::getValue, (v1, v2) -> v2)
        );
        for (Map.Entry<String, Double> entity:map.entrySet()) {
            System.out.println(entity);
        }
    }
}

class Pair<T1, T2> {
    private T1 key;
    private T2 value;

    public Pair(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public T1 getKey() {
        return key;
    }

    public void setKey(T1 key) {
        this.key = key;
    }

    public T2 getValue() {
        return value;
    }

    public void setValue(T2 value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}