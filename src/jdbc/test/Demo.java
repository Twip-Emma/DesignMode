package jdbc.test;

import java.sql.*;

public class Demo {
    // 适用于mysql8
    static String url="jdbc:mysql://链接/数据库";
    static String user="用户名";
    static String password="密码";
    public static void main(String[] args){
        //1.导入驱动jar包
        //2.注册驱动
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //3.获取数据库连接对象
            conn = DriverManager.getConnection(url,user,password);
            //4.定义sql
            String sql = "select * from user_info_new";
            //5.获取执行sql的对象Statement
            stmt = conn.createStatement();
            //6.执行sql
            ResultSet rs = stmt.executeQuery(sql);
            //7.处理结果
//            System.out.printf();
            int i = 1;
            while (rs.next()){
                System.out.print(i + ":");
                i++;
                System.out.print("id:" + rs.getString("user_id"));
                System.out.print("|coin:" + rs.getString("user_coin"));
                System.out.print("|health:" + rs.getString("user_health"));
                System.out.print("|crime:" + rs.getString("user_crime"));
                System.out.println("|max:" + rs.getString("user_coin_max"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            //8.释放资源
            try {
                if(conn!=null)
                {
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if(conn!=null)
                {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

