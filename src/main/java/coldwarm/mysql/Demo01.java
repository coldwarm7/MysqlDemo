package coldwarm.mysql;


import java.sql.*;

/**
 * Create by coldwarm on 2018/5/20.
 */

public class Demo01 {

    public static void main(String[] args) throws SQLException {
        Connection coon = new JDBCutil().getMysqlCoon();
//        add(coon);

//        delete(conn);

//        select(conn);

//        batch(conn);
    }

    public static void add(Connection coon) throws SQLException {
        String sql = "insert into t_user (username,pwd) values (?,?)";
        PreparedStatement ps = coon.prepareStatement(sql);
        ps.setString(1, "coldwarm000");
        ps.setString(2, "coldwarm111");
        ps.execute();
    }

    public static void delete(Connection coon) throws SQLException {
        String sql = "delete from t_user where id > ?";
        PreparedStatement ps = coon.prepareStatement(sql);
        ps.setObject(1,7);
        ps.execute();
    }

    public static void select(Connection coon) throws SQLException {
        String sql = "select id,username,pwd from t_user where id > ?";
        PreparedStatement ps = coon.prepareStatement(sql);
        ps.setObject(1,7);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "---" +
                    rs.getString(2) + "---" + rs.getString(3));
        }
    }

    //批处理  最好使用statement
    // PrepareStatement可能会导致预处理空间不足而报错
    public static void batch(Connection coon) throws SQLException {
        coon.setAutoCommit(false);   //手动提交

        Statement statement = coon.createStatement();
        for(int i = 0;i<10;i++){
            statement.addBatch("insert into t_user (username, pwd, regTime) " +
                    "values ('fz "+ i + " ' ,666,now())");
        }
        statement.executeBatch();
        coon.commit();
    }
}
