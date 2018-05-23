package coldwarm.mysql;

import java.io.*;
import java.sql.*;

/**
 * BLOB
 * Create by coldwarm on 2018/5/23.
 */

public class Demo04 {
    public static void main(String[] args) throws SQLException, IOException {
        Connection coon = new JDBCutil().getMysqlCoon();
//        addPicture(conn);
        selectHeadImg(coon);
    }

    public static void addPicture(Connection coon) throws SQLException, FileNotFoundException {
        PreparedStatement ps = coon.prepareStatement("insert into t_user (username,headImg) values (?,?)");
        ps.setObject(1,"xueluonanguo");
        ps.setBlob(2, new FileInputStream("D:\\dream\\dream.jpg"));

        ps.executeUpdate();
    }
    public static void selectHeadImg(Connection coon) throws SQLException, IOException {
        PreparedStatement ps = coon.prepareStatement("select * from t_user where id = ?");
        ps.setObject(1,4);

        ResultSet is = ps.executeQuery();
        while (is.next()){
            Blob c =is.getBlob("headImg");
            InputStream inputStream = c.getBinaryStream();
            OutputStream outputStream = new FileOutputStream("d:/jimu.jpg");
            int temp = 0;
            while ((temp = inputStream.read())!=-1){
                outputStream.write(temp);
            }
        }
    }
}
