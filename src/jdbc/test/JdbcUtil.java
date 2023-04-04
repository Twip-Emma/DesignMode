package jdbc.test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtil {
    private static volatile JdbcUtil instance;

    private JdbcUtil() {
    }

    public static JdbcUtil getInstance() {
        if (instance == null) {
            synchronized (JdbcUtil.class) {
                if (instance == null) {
                    instance = new JdbcUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 获取数据库连接
     *
     * @return Connection 数据库连接
     */
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://数据库连接/数据库名?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "用户名";
        String password = "密码";
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 关闭数据库连接
     *
     * @param conn 数据库连接
     */
    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 查询语句
     *
     * @param clazz 对应的实体类
     * @param sql   SQL语句
     * @param args  参数，如果没有则忽略
     * @param <T>   集合项类型
     * @return list 返回符合条件的结果集
     */
    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... args) throws SQLException {
        List<T> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            // 用于获取数据库连接
            conn = getConnection();

            // conn.prepareStatement(sql) 方法创建一个 PreparedStatement 对象 pstmt
            // 这个对象表示预编译的 SQL 语句，可以执行带参数的查询操作
            pstmt = conn.prepareStatement(sql);

            // 在 for 循环中，使用 pstmt.setObject() 方法为 pstmt 对象的参数占位符设置具体的参数值。
            // 然后执行查询操作，将结果保存在 ResultSet 类型的变量 rs 中
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1, args[i]);
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();

            // 接下来获取结果集元数据
            // 使用 getColumnCount() 方法获取列数
            int columnCount = metaData.getColumnCount();

            // 之后，遍历结果集，每遍历一行就创建一个 clazz 类型的对象 t，并使用反射为对象的属性赋值。
            // 在遍历完所有结果后，将 t 对象添加到 list 集合中，并返回 list
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = rs.getObject(columnName);
                    // 列名对应的属性名转换
                    Field field = clazz.getDeclaredField(underlineToCamel(String.valueOf(columnName)));
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return list;
    }


    /**
     * 执行修改相关的操作
     * @param sql SQL语句
     * @param args 参数列表
     * @return int 受影响行数
     */
    public int executeUpdate(String sql, Object... args) throws SQLException {
        int result = 0;
        Connection conn = getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return result;
    }


    /**
     * 工具方法：完成表下划线命名法转换为实体类小驼峰命名法
     * @param str 需要转换的列名
     * @return String 对应的小驼峰命名法的属性名
     */
    private static String underlineToCamel(String str) {
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
