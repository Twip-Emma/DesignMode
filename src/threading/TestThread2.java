package threading;

public class TestThread2 implements Runnable{

    @Override
    public void run() {
        for(int i = 1; i <= 20; i++){
            System.out.println("吃饭" + i);
        }
    }

    public static void main(String[] args) {
        TestThread2 thread2 = new TestThread2();
        new Thread(thread2).start();

        for (int i = 0; i<=20;i++){
            System.out.println("睡觉" + i);
        }
    }
}
