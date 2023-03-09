package look.test;

public class LockTest2 implements Runnable {
    static private volatile int i = 0;

    public void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            synchronized (this){
                increase();
                System.out.println(Thread.currentThread().getName() + "拿到了：" + i);
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class MyTest5{
    public static void main(String[] args) {
        try {
            LockTest2 worker = new LockTest2();
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
