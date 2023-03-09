package thread.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestPool1 extends Thread {
    private int i;

    public TestPool1(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(currentThread().getName() + "正在打印：" + i);
    }
}

class MyTest1 {
    // 普通的创建线程
    static void contextLoad1() {
        for (int i = 0; i < 10; i++) {
            TestPool1 myThread = new TestPool1(i);
            myThread.run();
        }
    }

    /**
     * 说明：初始化只有一个线程的线程池，内部使用LinkedBlockingQueue作为阻塞队列。
     * 特点：相当于单线程串行执行所有任务如果该线程异常结束，会重新创建一个新的线程继续执行任务，唯一的线程可以保证所提交任务的顺序执行
     */
    static void contextLoad2() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TestPool1(i));
        }
        executorService.shutdown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 说明：初始化一个指定线程数的线程池，其中corePoolSize == maxiPoolSize，使用LinkedBlockingQuene作为阻塞队列
     * 特点：每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，即使当线程池没有可执行任务时，
     * 也不会释放线程。如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
     */
    static void contextLoad3() {
        //设置分配多少个线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new TestPool1(i));
            executorService.execute(new TestPool1(i));
        }
        executorService.shutdown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 说明：初始化一个可以缓存线程的线程池，此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
     * 线程池的线程数可达到Integer.MAX_VALUE，即2147483647，内部使用SynchronousQueue作为阻塞队列；
     * 特点：在没有任务执行时，当线程的空闲时间超过keepAliveTime，默认为60s，会自动释放线程资源；
     * 当提交新任务时，如果没有空闲线程，则创建新线程执行任务，会导致一定的系统开销；
     * 因此，使用时要注意控制并发的任务数，防止因创建大量的线程导致而降低性能。
     */
    static void contextLoad4() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new TestPool1(i));
            executorService.execute(new TestPool1(i));
        }
        executorService.shutdown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        contextLoad1();
//        contextLoad2();
//        contextLoad3();
        contextLoad4();
    }
}