package thread.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestPool3 implements Runnable {

    private String command;

    public TestPool3(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(Thread.currentThread().getName() + "开始时间=" + sdf.format(date));
        processCommand();
        System.out.println(Thread.currentThread().getName() + "结束时间=" + sdf.format(date));
    }

    private void processCommand() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "TestPool3{" +
                "command='" + command + '\'' +
                '}';
    }
}

class MyTest3 {
    /**
     * 【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这
     * 样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
     */
    private static final int CORE_POOL_SIZE = 5;// 核心线程数
    private static final int MAX_POOL_SIZE = 10;// 最大线程数
    public static final int QUEUE_CAPACITY = 100;// 任务队列容量
    public static final Long KEEP_ALIVE_TIME = 1L;// 等待时间

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        for (int i = 0; i < 10; i++) {
            TestPool3 worker = new TestPool3("" + i);
            threadPoolExecutor.execute(worker);
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
        }
        System.out.println("完成了所有线程");
    }
}
