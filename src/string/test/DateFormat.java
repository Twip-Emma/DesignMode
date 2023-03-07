package string.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateFormat {
    // 日期格式化时，传入 pattern 中表示年份统一使用小写的 y。
    // 说明：日期格式化时，yyyy 表示当天所在的年，而大写的 YYYY 代表是 week in which year（JDK7 之后
    // 引入的概念），意思是当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，返回的 YYYY
    // 就是下一年。
    public static void main(String[] args) {
        // 获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(date));

        // 获取当前毫秒值
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);

        // 获取今年的天数
        int daysOfThisYear = LocalDate.now().lengthOfYear();
        // 获取指定某年的天数
        int i = LocalDate.of(2011, 1, 1).lengthOfYear();
        System.out.println(daysOfThisYear);
        System.out.println(i);
    }
}
