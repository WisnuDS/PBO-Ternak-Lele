package ternak.lele.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection connection = null;
    public static final String DBMS = "mysql";
    public static final String HOST = "128.199.196.208";
    public static final String USERNAME = "miqdad";
    public static final String PASSWORD = "monalisa123";
    public static final String DATABASE = "ternak_lele";

    public static Connection connectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = String.format("jdbc:%s://%s/%s", DBMS, HOST, DATABASE);
            Connection conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
            return conn;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
