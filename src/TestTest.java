class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class TestTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName("");
        System.out.println(user.getName());
        System.out.println("======");
        System.out.println((user.getName().getClass()));
        System.out.println("======");
        System.out.println("======");
    }
}
