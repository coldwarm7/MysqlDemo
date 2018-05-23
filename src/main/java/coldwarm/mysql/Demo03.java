package coldwarm.mysql;

import java.io.*;
import java.sql.*;

/**
 * CLOB测试  文本对象的使用
 * Create by coldwarm on 2018/5/23.
 */

public class Demo03 {
    public static void main(String[] args) throws SQLException, IOException {
        Connection coon = new JDBCutil().getMysqlCoon();
//        clobFromText(conn);
//        clobFromCode(conn);
//        selectMyinfo(conn);
    }

    public static void clobFromText(Connection coon) throws SQLException, FileNotFoundException {
        PreparedStatement ps = coon.prepareStatement("insert into t_user (username,myinfo) values  (?,?)");
        ps.setString(1,"jimu");
        ps.setClob(2,new FileReader(new File("d:/a.txt")));
        ps.executeUpdate();
    }

    public static void clobFromCode(Connection coon) throws SQLException {
        PreparedStatement ps = coon.prepareStatement("insert into t_user (username,myinfo) values  (?,?)");
        ps.setString(1,"cishu");

        ps.setClob(2,new BufferedReader((
                new InputStreamReader(new ByteArrayInputStream("aaaa".getBytes())))));
        ps.executeUpdate();
    }

    public static void selectMyinfo(Connection coon) throws SQLException, IOException {
        PreparedStatement ps = coon.prepareStatement("select * from t_user where id = ?");
        ps.setObject(1,1);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Clob c = rs.getClob("myinfo");
            Reader r = c.getCharacterStream();
            int temp = 0;
            while ((temp = r.read())!=-1){
                System.out.print((char) temp);
            }
        }
    }
}
