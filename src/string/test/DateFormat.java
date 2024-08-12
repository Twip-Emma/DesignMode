//package string.test;
//
//import org.junit.Test;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.Date;
//
//public class DateFormat {
//    // 日期格式化时，传入 pattern 中表示年份统一使用小写的 y。
//    // 说明：日期格式化时，yyyy 表示当天所在的年，而大写的 YYYY 代表是 week in which year（JDK7 之后
//    // 引入的概念），意思是当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，返回的 YYYY
//    // 就是下一年。
//    public static void main(String[] args) {
//        // 获取当前时间
//        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(dateFormat.format(date));
//
//        // 获取当前毫秒值
//        long currentTimeMillis = System.currentTimeMillis();
//        System.out.println(currentTimeMillis);
//
//        // 获取今年的天数
//        int daysOfThisYear = LocalDate.now().lengthOfYear();
//        // 获取指定某年的天数
//        int i = LocalDate.of(2011, 1, 1).lengthOfYear();
//        System.out.println(daysOfThisYear);
//        System.out.println(i);
//    }
//
//    @Test
//    public void test1() {
//        String s1 = "2020-02-12 20:55:09";
//        Date date = new Date();
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s1);
//        } catch (Exception e) {
//            System.out.println("错误");
//        }
//
//        String resp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
//        System.out.println(resp);
//    }
//
//    @Test
//    public void test2() {
//        String aStart = "2023-07-01";
//        String aEnd = "2023-07-06";
//        String bStart = "2023-07-05";
//        String bEnd = "2023-07-15";
//
//        boolean hasIntersection = hasIntersection(aStart, aEnd, bStart, bEnd);
//        System.out.println(hasIntersection); // 输出 true
//    }
//
//    public static boolean hasIntersection(String aStart, String aEnd, String bStart, String bEnd) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date startDateA = sdf.parse(aStart);
//            Date endDateA = sdf.parse(aEnd);
//            Date startDateB = sdf.parse(bStart);
//            Date endDateB = sdf.parse(bEnd);
//
//            // 判断两个时间间隔是否有交集
//            if (startDateA.compareTo(endDateB) <= 0 && endDateA.compareTo(startDateB) >= 0) {
//                return true;
//            }
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//}
