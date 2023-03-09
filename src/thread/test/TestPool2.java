package thread.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestPool2 extends Thread{
    private int i;

    public TestPool2(int i){
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            System.out.println(currentThread().getName() + "打印编号：" + i + "=====>" + sdf.format(date));
        }
    }
}

final class MyTest2{
    /**
     * 特定：初始化的线程池可以在指定的时间内周期性的执行所提交的任务，在实际的业务场景中可以使用该线程池定期的同步数据。
     * 总结：除了newScheduledThreadPool的内部实现特殊一点之外，其它线程池内部都是基于ThreadPoolExecutor类（Executor的子类）实现的。
     */
    static void contextLoad5(){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        for (int i = 0; i < 5; i++) {
            scheduledThreadPoolExecutor.execute(new TestPool2(i));
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * 程序正常结束，且线程3被重复利用，并没达到线程池的最大容量4。
     * 我们可以这样认为，newScheduledThreadPool这线程池可以使只执行一遍的线程以一定速率循环执行，
     * 但是如果以execute方式提交线程则不会重复执行。
     */
    static void contextLoad6(){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4);
        for (int i = 0; i < 5; i++) {
            scheduledThreadPoolExecutor.execute(new TestPool2(i));
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * 我们可以发现线程2实现了重复利用，虽然创建的线程是一次执行，但却实现了重复执行的效果，这就是该线程池最大的特点。
     */
    static void contextLoad7(){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4);
        for (int i = 0; i < 5; i++) {
            // initialDelay表示首次执行任务的延迟时间，
            // period表示每次执行任务的间隔时间，
            // TimeUnit.MILLISECONDS执行的时间间隔数值单位
            scheduledThreadPoolExecutor.scheduleAtFixedRate(new TestPool2(i),
                    1000, 2000, TimeUnit.MICROSECONDS);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        contextLoad5();
//        contextLoad6();
        contextLoad7();
    }
}
