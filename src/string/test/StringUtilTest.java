package string.test;

public class StringUtilTest {
    public static void main(String[] args) {
        StringUtilBooleanTest test = new StringUtilBooleanTest();
        if (Boolean.TRUE.equals(test.isOK())) {
            System.out.println("成功");
        }
    }

    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}

class StringUtilBooleanTest {
    private Boolean isOK;

    public Boolean isOK() {
        return isOK;
    }

    public void setOK(Boolean OK) {
        isOK = OK;
    }
}
