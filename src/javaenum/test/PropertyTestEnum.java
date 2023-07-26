package javaenum.test;

import java.io.Serializable;

public enum PropertyTestEnum implements Serializable {
    VIEWER,USER,ADMIN,SUPER
}


class test {
    public static void main(String[] args) {
        System.out.println(PropertyTestEnum.USER);
        System.out.println(PropertyTestEnum.VIEWER.ordinal());
        System.out.println(PropertyTestEnum.valueOf(null));
    }
}
