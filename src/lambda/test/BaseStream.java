//package lambda.test;
//
//import org.junit.Test;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//
//public class BaseStream {
//    @Test
//    public void test1() {
//        /*
//          forEach
//          遍历
//         */
//        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        numberList.forEach(number -> System.out.print(number + ","));
//    }
//
//    @Test
//    public void test2() {
//        /*
//          使用 map 把对象一对一映射成另一种对象或者形式。
//          相当于：key是原来的值，value是处理之后的值
//          map是一对一映射
//          有时候会出现一对多的映射，这时候就用flatmap
//         */
//        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        List<Integer> collect = numberList.stream().map(num -> num * 2).toList();
//        collect.forEach(System.out::println);
//    }
//
//    @Test
//    public void test3() {
//        /*
//          使用 filter 进行数据筛选，挑选出想要的元素，下面的例子演示怎么挑选出偶数数字。
//         */
//        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        List<Integer> collect = numberList.stream().filter(num -> num % 2 == 0).toList();
//        collect.forEach(System.out::println);
//    }
//
//    @Test
//    public void test4() {
//        /*
//          findFirst 可以查找出 Stream 流中的第一个元素，它返回的是一个 Optional 类型
//          findFirst 方法在查找到需要的数据之后就会返回不再遍历数据了
//          也因此 findFirst 方法可以对有无限数据的 Stream 流进行操作
//          也可以说 findFirst 是一个 short-circuiting 操作。
//         */
//        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        Optional<Integer> first = numberList.stream().findFirst();
//        System.out.println(first.orElse(-1));
//    }
//
//    @Test
//    public void test5() {
//        /*
//          collect / toArray
//          Stream 流可以轻松的转换为其他结构
//         */
//        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        // to array
//        Integer[] toArray = numberList.toArray(Integer[]::new);
//        System.out.println(Arrays.toString(toArray));
//        // to List
//        List<Integer> integerList = numberList.stream().toList();
//        System.out.println(integerList);
//        // to set
//        Set<Integer> integerSet = new HashSet<>(numberList);
//        System.out.println(integerSet);
//        // to String
//        String toString = numberList.stream().map(String::valueOf).collect(Collectors.joining());
//        System.out.println("toString:" + toString);
//        // to String split by ,
//        String toString2 = numberList.stream().map(String::valueOf).collect(Collectors.joining(","));
//        System.out.println("toString2:" + toString2);
//    }
//
//    @Test
//    public void test6() {
//        /*
//            limit / skip
//            limit：获取前面多少个元素
//            skip:跳过前面多少个元素
//         */
//        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        Stream<Integer> limit = numberList.stream().limit(5);
//        Stream<Integer> skip = numberList.stream().skip(5);
//        System.out.println(limit.map(String::valueOf).collect(Collectors.joining(",")));
//        System.out.println(skip.map(String::valueOf).collect(Collectors.joining(",")));
//    }
//
//    @Test
//    public void test7() {
//        /*
//            Statistics
//            数学统计功能，求一组数组的最大值、最小值、个数、数据和、平均数等。
//         */
//        //数学计算测试
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        //mapToInt是将元素映射成int类型
//        IntSummaryStatistics stats = list.stream().mapToInt(x -> x).summaryStatistics();
//        System.out.println("最大值:" + stats.getMax());
//        System.out.println("最小值：" + stats.getMin());
//        System.out.println("个数：" + stats.getCount());
//        System.out.println("和：" + stats.getSum());
//        System.out.println("平均数：" + stats.getAverage());
//    }
//
//    @Test
//    public void test8() {
//        /*
//            groupingBy
//            分组聚合功能，和数据库的 Group by 的功能一致。
//         */
//        List<Integer> ageList = Arrays.asList(11, 12, 13, 22, 23, 24);
//        Map<String, List<Integer>> ageGroupMap = ageList.stream().collect(Collectors.groupingBy(age -> String.valueOf(age / 10)));
//        ageGroupMap.forEach((k, v) -> System.out.println("年龄：" + k + "0多岁的有：" + v));
//    }
//
//    @Test
//    public void test9() {
//        /*
//            partitioningBy
//            按某个条件分组
//            给一组年龄，分出成年人和未成年人
//         */
//        //按某个条件分组，给一组年龄，分出成年人和未成年人
//        List<Integer> ageList = Arrays.asList(12, 23, 45, 17, 58, 35);
//        Map<Boolean, List<Integer>> ageMap = ageList.stream().collect(Collectors.partitioningBy(age -> age > 18));
//        System.out.println("未成年人：" + ageMap.get(false));
//        System.out.println("成年人：" + ageMap.get(true));
//    }
//
//    /**
//     * 创建流的多种方式
//     */
//    public static void createStream() throws FileNotFoundException {
//        //数组转集合
//        List<String> nameList = Arrays.asList("Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter");
//        //数组
//        String[] nameArr = {"Darcy", "Chris", "Linda", "Sid", "Kim", "Jack", "Poul", "Peter"};
//        //集合获取串行Stream流
//        Stream<String> nameListStream = nameList.stream();
//        //集合获取并行Stream流
//        Stream<String> nameListStream2 = nameList.parallelStream();
//        //数组获取Stream流
//        Stream<String> nameAttrStream = Stream.of(nameArr);
//        //数组获取Stream流
//        Stream<String> nameAttrStream2 = Arrays.stream(nameArr);
//        //文件流获取Stream流
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("data_save.data"));
//        Stream<String> linesStream = bufferedReader.lines();
//        //从静态方法获取流操作
//        IntStream rangeStream = IntStream.range(1, 10);
//        rangeStream.limit(10).forEach(num -> System.out.println(num + ","));
//    }
//}
