package coldwarm.mysql;

import java.sql.*;

import static java.lang.System.*;

/**
 * Create by coldwarm on 2018/5/23.
 */
//sql事务测试

public class Demo02 {
    public static void main(String[] args) throws SQLException {
        transaction();
    }


    public static void transaction() throws SQLException {
        Connection coon = new JDBCutil().getMysqlCoon();
        try {
            String sql1 = "insert into t_user (username,pwd,regTime) values (?,?,?)";
            PreparedStatement ps1 = coon.prepareStatement(sql1);
            ps1.setString(1, "coldwarm000");
            ps1.setString(2, "coldwarm111");
            Timestamp timestamp1 = new Timestamp(System.currentTimeMillis()); //需要将long类型转换为Timestamp类型
            ps1.setTimestamp(3, timestamp1);
            ps1.execute();

            String sql2 = "insert into t_user (username,pwd,regTime) values (?,?,?)";
            PreparedStatement ps2 = coon.prepareStatement(sql2);
            ps2.setString(1, "cishu");
            ps2.setString(2, "coldwarm111");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            ps2.setTimestamp(3, timestamp2);
            ps2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                coon.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }

    }
}