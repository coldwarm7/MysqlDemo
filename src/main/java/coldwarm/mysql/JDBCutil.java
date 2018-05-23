package coldwarm.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Create by coldwarm on 2018/5/23.
 */

public class JDBCutil {
    static Properties properties = null;

    static {
        properties = new Properties();
        try {
            properties.load(Thread.currentThread().
                    getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getMysqlCoon(){
        try {
            Class.forName(properties.getProperty("mysqlDriver"));
            return DriverManager.getConnection(
                    properties.getProperty("mysqlURL"),
                    properties.getProperty("mysqlUser"),
                    properties.getProperty("mysqlPwd"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
