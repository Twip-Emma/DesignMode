package thread.test;

public class TestThread1 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i <= 20; i++){
            System.out.println("吃饭" + i);
        }
    }

    public static void main(String[] args) {

        TestThread1 thread1 = new TestThread1();
        thread1.start();
        TestThread1 thread2 = new TestThread1();
        thread2.start();
        for (int i = 1; i <= 20; i++){
            System.out.println("睡觉" + i);
        }
    }
}
