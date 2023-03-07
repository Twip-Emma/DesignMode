package string.test;

public class StringBuild {
    public static void main(String[] args) {
        // 循环体内，字符串的连接方式，使用 StringBuilder 的 append 方法进行扩展。
        StringBuilder sb = new StringBuilder("start");
        for (int i = 0; i < 10; i++) {
            sb.append("+hello");
        }
        System.out.printf(sb.toString());
    }
}
