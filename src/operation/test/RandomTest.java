package operation.test;

import java.util.Random;

public class RandomTest {
    /**
     * 【强制】注意 Math.random() 这个方法返回是 double 类型，注意取值的范围 0≤x<1（能够
     * 取到零值，注意除零异常），如果想获取整数类型的随机数，不要将 x 放大 10 的若干倍然后
     * 取整，直接使用 Random 对象的 nextInt 或者 nextLong 方法。
     */
    public static void main(String[] args) {
        // 相同seed结果一样
        Random random1 = new Random(1);
        Random random2 = new Random(1);
        Random random3 = new Random(1);

        for (int j = 0; j < 30; j++) {
            System.out.print(random1.nextInt(10));
            System.out.print("+");
            System.out.print(random2.nextInt(10));
            System.out.print("+");
            System.out.println(random3.nextLong(10));
        }
    }
}
