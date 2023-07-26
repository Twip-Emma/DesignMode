package callback.test;

public class Test3 {
    public static void main(String[] args) {
        Son son = new Son();
        Father f = son.fa();
        System.out.println(f.getClass());
    }

    static class Father {
        Father me() {
            System.out.println("Father me()");
            return this;
        }
    }

    static class Son extends Father {
        Father fa() {
            System.out.println("Father fa()");
            return super.me();
        }
    }
}
