package demo.exp;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 面试题：我们知道文件存储可以存储一些数据，我们现在想要利用文件存储的方法，来构建一类类似于redis的持久化存储类。
 * 它可以存储不同类型的对象，并且可以设置过期时间，当过期时间到达时，对象会被自动删除或不可访问。
 * 注意，这里的存储对象期望可以是尽可能支持广泛类型的对象，而不仅仅是特定的类型的对象。
 * 请实现以下的DataSave类的save和load方法以实现我们的目标，并保证unitest方法中的测试通过。（可以添加其他的辅助方法及类）
 * <p>
 * 提示：实现以下问题的方法很多，并没有唯一答案，请尽可能提供简洁的实现。我们重点关注代码的可读性和可维护性及思路。
 * <p>
 * 提交格式：请提供实现的代码，并且提供运行结果的截图。
 */
public class DataSave {
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private boolean stopCleaner = false;

    private boolean stopPersistence = false;

    private final String dataFileName = "data_save.data";

    private final String path;

    private final Map<String, Value> cache;

    public DataSave() {
        this("data", 16);
    }

    public DataSave(String path, int mapCapacity) {
        this.path = path;
        new File(path).mkdirs();
        String filePath = this.path + File.separator + this.dataFileName;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                this.cache = (HashMap) readObj(filePath);
            } else {
                file.createNewFile();
                this.cache = new HashMap<>(mapCapacity);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.startCleaner();
        this.startPersistence();
    }

    private static final class Value implements Serializable {
        private final long exp;
        private final Object value;

        private Value(int exp, Object value) {
            this.exp = expireAt(exp);
            this.value = value;
        }
    }

    //请实现持久化存储函数（使用文件存储相关方法）

    /**
     * @param key    存储的key
     * @param s      存储的对象
     * @param expire 过期时间，单位秒，如果为0则表示永不过期
     */
    public void save(String key, Object s, int expire) {
        /*
         * 你的代码
         */
        Value v = new Value(expire, s);
        try {
            rwLock.writeLock().lock();
            cache.put(key, v);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    //请实现持久化数据的取出

    /**
     * @param key 存储的key
     * @return 存储的对象
     */
    public Object load(String key) {
        /*
         * 你的代码
         */
        Value o = null;
        try {
            rwLock.readLock().lock();
            o = cache.get(key);
        } finally {
            rwLock.readLock().unlock();
        }
        if (o == null || isExpired(o.exp)) {
            return null;
        }
        return o.value;
    }

    public void shutdown() {
        this.stopCleaner = true;
        this.stopPersistence = true;
        rdb();
    }

    private void startCleaner() {
        new Thread(() -> {
            while (!this.stopCleaner) {
                clean();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void clean() {
        try {
            this.rwLock.writeLock().lock();
            this.cache.entrySet().removeIf(entry -> isExpired(entry.getValue().exp));
        } finally {
            this.rwLock.writeLock().unlock();
        }
    }

    private void startPersistence() {
        new Thread(() -> {
            while (!this.stopPersistence) {
                rdb();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void rdb() {
        try {
            this.rwLock.readLock().lock();
            writeObj(this.path + File.separator + this.dataFileName, this.cache);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.rwLock.readLock().unlock();
        }
    }

    private static long expireAt(int exp) {
        if (exp == 0) {
            return 0;
        }
        return System.currentTimeMillis() + exp * 1000L;
    }

    private static boolean isExpired(long targetExp) {
        if (targetExp == 0) {
            return false;
        }
        return System.currentTimeMillis() > targetExp;
    }

    private static Object readObj(String filename) throws IOException, ClassNotFoundException {
        return new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    private static void writeObj(String filename, Object obj) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(obj);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        unitest();
    }

    static void unitest() throws IOException, ClassNotFoundException {
        School sc = new School("wuhan", "wuhan location");
        Clazz c = new Clazz("1", 30, 2, sc);
        Student s = new Student("zhangsan", 18, c);
        Student s0 = new Student("lisi", 22, c);

        //存储和取出学生对象
        DataSave sds = new DataSave();
        sds.save("student", s, 0);
        Student s2 = (Student) (sds.load("student"));
        System.out.println("age:" + s2.age);
        System.out.println("grade:" + s2.clazz.grade);
        System.out.println("address:" + s2.clazz.school.address);

        //存储和取出班级对象
        sds.save("clazz", c, 0);
        Clazz c2 = (Clazz) (sds.load("clazz"));
        System.out.println("grade:" + c2.grade);
        System.out.println("address:" + c2.school.address);

        //存储和取出学校对象
        sds.save("school", sc, 0);
        School sc2 = (School) (sds.load("school"));
        System.out.println("address:" + sc2.address);

        //存储和取出学生列表
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(s);
        students.add(s0);
        sds.save("students", students, 0);
        ArrayList<Student> students2 = (ArrayList<Student>) (sds.load("students"));
        System.out.println("students size:" + students2.size());
        System.out.println("students1 age:" + students2.get(0).age);


        //存储和取出学生对象，过期时间为10秒
        sds.save("school_test", sc, 10);
        School sc3 = (School) (sds.load("school_test"));
        System.out.println("未过期时，school:" + sc3);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        School sc4 = (School) (sds.load("school_test"));
        System.out.println("已过期时，school:" + (sc4 == null));

        sds.shutdown();
    }
}


class Student implements Serializable {
    String name;
    int age;
    Clazz clazz;

    public Student(String name, int age, Clazz clazz) {
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }
}

class Clazz implements Serializable {
    String grade;
    int studentNumbers;
    int teacherNumbers;
    School school;

    public Clazz(String grade, int studentNumbers, int teacherNumbers, School school) {
        this.grade = grade;
        this.studentNumbers = studentNumbers;
        this.teacherNumbers = teacherNumbers;
        this.school = school;
    }
}

class School implements Serializable {
    String name;
    String address;

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }
}