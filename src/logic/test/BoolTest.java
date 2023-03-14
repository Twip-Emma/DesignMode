package logic.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BoolTest {
    /**
     * 【推荐】除常用方法（如 getXxx/isXxx）等外，不要在条件判断中执行其它复杂的语句，将复
     * 杂逻辑判断的结果赋值给一个有意义的布尔变量名，以提高可读性。
     * 说明：很多 if 语句内的逻辑表达式相当复杂，与、或、取反混合运算，甚至各种方法纵深调用，理解成本
     * 非常高。如果赋值一个非常好理解的布尔变量名字，则是件令人爽心悦目的事情。
     */
    public static void main(String[] args) {
        long time = new Date().getTime() / 1000;
        final boolean isEven = (time % 2 == 0);
        if (isEven) {
            System.out.println("【偶数】当前时间秒数：" + time);
        } else {
            System.out.println("【奇数】当前时间秒数：" + time);
        }
    }
}
