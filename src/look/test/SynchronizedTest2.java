package look.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronizedTest2 implements Runnable{
    /**
     * 共享资源
     */
    static int i = 0;

    public synchronized void increase(){
        i++;
        System.out.println(Thread.currentThread().getName() + "拿到了：" + i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            increase();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class MyTest2{
    public static void main(String[] args) {
        try {
            SynchronizedTest2 worker = new SynchronizedTest2();
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
