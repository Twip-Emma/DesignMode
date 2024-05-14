package object.test;

public class SetterTest {
    public static void main(String[] args) {
        Group group = new Group("七画一只妖");
        UserResponse userResponse = new UserResponse(group);
        Group group1 = userResponse.getGroup();
        group1.setId(group1.getId().replace("一只妖", ""));


        System.out.println(userResponse.getGroup().getId());

    }
}

class UserResponse {
    private Group group;

    public UserResponse(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

class Group {
    private String id;

    public Group(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}