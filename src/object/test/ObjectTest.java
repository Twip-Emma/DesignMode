package object.test;

import java.util.logging.Logger;

public class ObjectTest {
    public static void main(String[] args) {
        Logger log = Logger.getLogger("log");
        log.info("test");

        log.info(String.valueOf(Record.class.isAssignableFrom(MyRecord.class)));
        log.info(String.valueOf(MyRecord.class.isRecord()));
        MyRecord myRecord = new MyRecord("张三", 18);
        log.info(String.valueOf(myRecord));
        log.info(myRecord.username());

        log.info("test2");
    }
}
