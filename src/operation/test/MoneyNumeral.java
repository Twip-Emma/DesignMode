package operation.test;

import java.math.BigDecimal;

public class MoneyNumeral {
    public static void main(String[] args) {
        // 指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的。
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        float diff = 1e-6F;
        if (Math.abs(a - b) < diff) {
            System.out.println("true");
        }

        // 使用 BigDecimal 来定义值，再进行浮点数的运算操作。
        BigDecimal a1 = new BigDecimal("1.0");
        BigDecimal b1 = new BigDecimal("0.9");
        BigDecimal c1 = new BigDecimal("0.8");
        BigDecimal x = a1.subtract(b1);
        BigDecimal y = b1.subtract(c1);
        if (x.compareTo(y) == 0) {
            System.out.println("true");
        }
    }
}
