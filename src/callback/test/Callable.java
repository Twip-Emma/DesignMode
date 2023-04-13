package callback.test;

public interface Callable {
    /**
     * 回调接口
     */
    void call();
}


/**
 * 定义类Mother实现了Callable接口，实现了call()回调函数的具体内容
 * (同步)
 */
class Mother implements Callable {
    Son son;
    public Mother(Son son)
    {
        this.son=son;
    }
    //表示妈妈和儿子的分别函数，儿子在这期间搭乘火车离开
    public void parting()
    {
        System.out.println("开始执行同步回调函数");
        son.rideTrain(this);
        System.out.println("同步回调函数执行完成");
    }
    @Override
    public void call() {
        System.out.println("儿子到学校了");
    }
}


/**
 * 定义类Mother实现了Callable接口，实现了call()回调函数的具体内容
 * (异步)
 */
class Mother2 implements Callable {

    Son son;

    public Mother2(Son son)
    {
        this.son=son;
    }

    public void parting()
    {
        System.out.println("开始执行异步回调函数");
        new Thread(() -> son.rideTrain(Mother2.this)).start();
        System.out.println("异步回调函数执行完成");//开启线程处理儿子坐火车的函数，这条打印语句会在回调函数执行完成前执行。
    }

    @Override
    public void call() {
        System.out.println("儿子到学校了");
    }
}



/**
 * 儿子类
 */
class Son {
    public void rideTrain(Callable callable)
    {
        try {
            //模拟坐火车
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        callable.call();//到了打电话给妈妈
    }
}


/**
 * 测试类
 */
class Test {
    public static void main(String[] args) {
        // Mother1 同步，Mother2 异步

        Son jack=new Son();
        Mother2 mother=new Mother2(jack);
        mother.parting();
    }
}
