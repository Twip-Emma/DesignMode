package demo.exp;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 面试题：我们知道文件存储可以存储一些数据，我们现在想要利用文件存储的方法，来构建一类类似于redis的持久化存储类。
 * 它可以存储不同类型的对象，并且可以设置过期时间，当过期时间到达时，对象会被自动删除或不可访问。
 * 注意，这里的存储对象期望可以是尽可能支持广泛类型的对象，而不仅仅是特定的类型的对象。
 * 请实现以下的DataSave类的save和load方法以实现我们的目标，并保证unitest方法中的测试通过。（可以添加其他的辅助方法及类）
 *
 * 提示：实现以下问题的方法很多，并没有唯一答案，请尽可能提供简洁的实现。我们重点关注代码的可读性和可维护性及思路。
 *
 * 提交格式：请提供实现的代码，并且提供运行结果的截图。
 */
public class DataSave2 {

    private static final String SAVE_DIRECTORY = "data"; // 存储数据的目录
    private static final String SAVE_FILE_NAME = "save.data"; // 存储数据的文件名

    // 使用读写锁, 在读取数据时可以允许多个线程同时访问，而在写入数据时只能有一个线程进行访问
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //请实现持久化存储函数（使用文件存储相关方法）
    /**
     * @param key 存储的key
     * @param s 存储的对象
     * @param expire 过期时间，单位秒，如果为0则表示永不过期
     */
    void save(String key,Object s,int expire)
    {
        /**
         * 你的代码
         */
        lock.writeLock().lock(); // 获取写锁
        try {
            Map<String, ObjectWithExpire> data = loadDataMap(); // 加载已有数据
            data.put(key, new ObjectWithExpire(s, expire)); // 更新数据
            try{
                File directory = new File(SAVE_DIRECTORY);
                if (!directory.exists()) {
                    directory.mkdir(); // 创建存储数据的目录
                }
                String filePath = SAVE_DIRECTORY + "/" + SAVE_FILE_NAME;
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
                oos.writeObject(data);

            }catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            lock.writeLock().unlock(); // 释放写锁
        }
    }

    //请实现持久化数据的取出
    /**
     * @param key 存储的key
     * @return 存储的对象
     */
    Object load(String key){
        /**
         * 你的代码
         */
        lock.readLock().lock(); // 获取读锁
        try {
            Map<String, ObjectWithExpire> data = loadDataMap(); // 加载已有数据
            ObjectWithExpire objectWithExpire = data.get(key);
            if (objectWithExpire != null && !objectWithExpire.isExpired()){
                return objectWithExpire.object;
            }else {
                lock.readLock().unlock(); // 在删除数据之前释放读锁
                lock.writeLock().lock(); // 获取写锁
                try {
                    data = loadDataMap(); // 重新加载数据以确保一致性
                    data.remove(key); // 删除过期的数据
                    saveDataMap(data);
                } finally {
                    lock.writeLock().unlock(); // 释放写锁
                    lock.readLock().lock(); // 重新获取读锁
                }
                return null;
            }
        } finally {
            lock.readLock().unlock(); // 释放读锁
        }
    }

    /**
     * 加载文件到 map
     */
    private Map<String, ObjectWithExpire> loadDataMap() {
        lock.readLock().lock(); // 获取读锁
        try{
            File file = new File(SAVE_DIRECTORY + "/" + SAVE_FILE_NAME);
            if (file.exists()){
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                Map<String, ObjectWithExpire> map = (Map<String, ObjectWithExpire>) objectInputStream.readObject();
                objectInputStream.close();
                return map;
            } else {
                return new HashMap<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        } finally {
            lock.readLock().unlock(); // 释放读锁
        }
    }

    /**
     * map 写入文件
     */
    private void saveDataMap(Map<String, ObjectWithExpire> data) {
        lock.writeLock().lock(); // 获取写锁
        try {
            String filePath =  SAVE_DIRECTORY + "/" + SAVE_FILE_NAME;
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filePath));
            objectOut.writeObject(data);
            objectOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock(); // 释放写锁
        }
    }

    public static void main(String[] args) {
        unitest();
    }

    static void unitest()
    {
        School2 sc=new School2("wuhan","wuhan location");
        Clazz2 c=new Clazz2("1",30,2,sc);
        Student2 s=new Student2("zhangsan",18,c);
        Student2 s0=new Student2("lisi",22,c);

        //存储和取出学生对象
        DataSave2 sds=new DataSave2();
        sds.save("student2",s,0);
        Student2 s2=(Student2)(sds.load("student2"));
        System.out.println("age:"+s2.age);
        System.out.println("grade:"+s2.clazz.grade);
        System.out.println("address:"+s2.clazz.school.address);

        //存储和取出班级对象
        sds.save("clazz",c,0);
        Clazz2 c2=(Clazz2)(sds.load("clazz"));
        System.out.println("grade:"+c2.grade);
        System.out.println("address:"+c2.school.address);

        //存储和取出学校对象
        sds.save("school",sc,0);
        School2 sc2=(School2)(sds.load("school"));
        System.out.println("address:"+sc2.address);

        //存储和取出学生列表
        ArrayList<Student2> students=new ArrayList<Student2>();
        students.add(s);
        students.add(s0);
        sds.save("students",students,0);
        ArrayList<Student2> students2=(ArrayList<Student2>)(sds.load("students"));
        System.out.println("students size:"+students2.size());
        System.out.println("students1 age:"+students2.get(0).age);



        //存储和取出学生对象，过期时间为10秒
        sds.save("school_test",sc,2);
        School2 sc3=(School2)(sds.load("school_test"));
        System.out.println("未过期时，school:"+sc3);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        School2 sc4=(School2)(sds.load("school_test"));
        System.out.println("已过期时，school:"+(sc4==null));

    }
}


/**
 * 辅助类：用于封装对象和过期时间，方便在保存数据时记录对象的过期时间。
 */
class ObjectWithExpire implements Serializable {
    Object object;
    long expireTime; // 过期时间，单位：毫秒

    public ObjectWithExpire(Object object, long expireTime) {
        this.object = object;
        this.expireTime = expireTime > 0 ? System.currentTimeMillis() + expireTime * 1000 : 0;
    }

    public Boolean isExpired() {
        return this.expireTime > 0 && this.expireTime < System.currentTimeMillis();
    }
}

class Student2 implements Serializable
{
    String name;
    int age;
    Clazz2 clazz;
    public Student2(String name, int age, Clazz2 clazz)
    {
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }
}

class Clazz2 implements Serializable {
    String grade;
    int studentNumbers;
    int teacherNumbers;
    School2 school;
    public Clazz2(String grade, int studentNumbers, int teacherNumbers, School2 school)
    {
        this.grade = grade;
        this.studentNumbers = studentNumbers;
        this.teacherNumbers = teacherNumbers;
        this.school = school;
    }
}
class School2 implements Serializable {
    String name;
    String address;
    public School2(String name, String address)
    {
        this.name = name;
        this.address = address;
    }
}