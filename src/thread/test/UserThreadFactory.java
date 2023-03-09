package thread.test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class UserThreadFactory implements ThreadFactory {
    /**
     * 创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。
     * 正例：自定义线程工厂，并且根据外部特征进行分组，比如，来自同一机房的调用，把机房编号赋值给
     * whatFeatureOfGroup
     */
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);
    // 定义线程组名称，在利用 jstack 来排查问题时，非常有帮助
    UserThreadFactory(String whatFeatureOfGroup) {
        namePrefix = "From UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }
    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0, false);
        System.out.println(thread.getName());
        return thread;
    }
}
