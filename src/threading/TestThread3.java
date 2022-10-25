package threading;

public class TestThread3 implements Runnable{

    private static int ticketNum = 10;
    private final Object lock = new Object();

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){
                if (ticketNum > 0){
                    System.out.println(Thread.currentThread().getName() + " --> 拿到了第" + ticketNum + "票");
                    ticketNum--;
                }else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        TestThread3 thread3 = new TestThread3();

        new Thread(thread3, "哦豁").start();
        new Thread(thread3, "富贵").start();
        new Thread(thread3, "黄牛党").start();
    }
}
