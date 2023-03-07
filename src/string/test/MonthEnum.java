package string.test;

public enum MonthEnum {
    //1.提供当前枚举类的对象
    JANUARY("01"),
    FEBRUARY("02"),
    MARCH("03"),
    APRIL("04"),
    MAY("05"),
    JUNE("06"),
    JULY("07"),
    AUGUST("08"),
    SEPTEMBER("09"),
    OCTOBER("10"),
    NOVEMBER("11"),
    DECEMBER("12");

    //2.声明Month对象的属性：private final 修饰
    private final String months;

    //3.私有化类的构造器,并给对象属性赋值
    MonthEnum(String months){
        this.months = months;
    }
}
