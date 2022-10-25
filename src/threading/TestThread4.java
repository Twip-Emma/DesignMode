package threading;

public class TestThread4 implements Runnable{

    private static int ticketNum = 10;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buyTicket();
        }
    }

    public synchronized void buyTicket(){
        if (ticketNum > 0){
            System.out.println(Thread.currentThread().getName() + " --> 拿到了第" + ticketNum + "票");
            ticketNum--;
        }
    }

    public static void main(String[] args) {
        TestThread4 thread4 = new TestThread4();

        new Thread(thread4, "哦豁").start();
        new Thread(thread4, "富贵").start();
        new Thread(thread4, "黄牛党").start();
    }
}
