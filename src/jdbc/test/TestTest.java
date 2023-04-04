package jdbc.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class User{
    public User() {
    }

    private String userId;
    private Integer userCoin;
    private Integer userHealth;
    private Integer userCrime;
    private Integer userCoinMax;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserCoin() {
        return userCoin;
    }

    public void setUserCoin(Integer userCoin) {
        this.userCoin = userCoin;
    }

    public Integer getUserHealth() {
        return userHealth;
    }

    public void setUserHealth(Integer userHealth) {
        this.userHealth = userHealth;
    }

    public Integer getUserCrime() {
        return userCrime;
    }

    public void setUserCrime(Integer userCrime) {
        this.userCrime = userCrime;
    }

    public Integer getUserCoinMax() {
        return userCoinMax;
    }

    public void setUserCoinMax(Integer userCoinMax) {
        this.userCoinMax = userCoinMax;
    }

    public User(String userId, Integer userCoin, Integer userHealth, Integer userCrime, Integer userCoinMax) {
        this.userId = userId;
        this.userCoin = userCoin;
        this.userHealth = userHealth;
        this.userCrime = userCrime;
        this.userCoinMax = userCoinMax;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userCoin='" + userCoin + '\'' +
                ", userHealth='" + userHealth + '\'' +
                ", userCrime='" + userCrime + '\'' +
                ", userCoinMax='" + userCoinMax + '\'' +
                '}';
    }
}

public class TestTest {
    public static void main(String[] args) throws SQLException {
        // 获取单例对象
        JdbcUtil jdbcUtil = JdbcUtil.getInstance();

        String sql2 = "UPDATE user_info_new SET user_health = 666 WHERE user_id = ?";
        int i = jdbcUtil.executeUpdate(sql2, "1157529280");
        System.out.println(i);

        // 查询所有用户信息
        String sql = "SELECT * FROM user_info_new where user_id = ?";
        List<User> userList = jdbcUtil.executeQuery(User.class, sql, "1157529280");

        // 遍历输出所有用户信息
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
