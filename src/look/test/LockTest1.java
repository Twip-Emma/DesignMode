package look.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest1 implements Runnable {

    static private int i = 0;
    private Lock lock = new ReentrantLock();

    public void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            try {
                lock.lock();
                increase();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了：" + i);
        }
    }
}

class MyTest4 {
    public static void main(String[] args) {
        try {
            LockTest1 worker = new LockTest1();
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
