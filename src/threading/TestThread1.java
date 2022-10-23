package threading;

public class TestThread1 extends Thread{
    @Override
    public void run() {
        for(int i = 0; i <= 20; i++){
            System.out.println("吃饭" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++){
            System.out.println("睡觉" + i);
        }
    }
}
