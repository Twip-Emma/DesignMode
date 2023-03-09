package look.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronizedTest1 implements Runnable {
    /**
     * 共享资源
     */
    static int i = 0;

    public void increase() {
        i++;
    }

    @Override
    public synchronized void run() {
        for (int j = 0; j < 10; j++) {
            increase();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "拿到了：" + i);
        }
    }
}

class MyTest1 {
    public static void main(String[] args) {
        try {
            SynchronizedTest1 worker = new SynchronizedTest1();
            Thread t1 = new Thread(worker);
            Thread t2 = new Thread(worker);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
