package callback.test;

public interface Callable {
    /**
     * 回调接口
     */
    void call();
}


/**
 * 定义类Mother实现了Callable接口，实现了call()回调函数的具体内容
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
        Son jack=new Son();
        Mother mother=new Mother(jack);
        mother.parting();
    }
}
