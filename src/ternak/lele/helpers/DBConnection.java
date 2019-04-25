package ternak.lele.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection connection = null;

    public static Connection connectDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://ternak-lele.mysql.database.azure.com:3306/ternak_lele?useSSL=true&requireSSL=false";
            Connection conn = DriverManager.getConnection(url, "miqdadyyy@ternak-lele", "Monalisa123");
            return conn;
        } catch (ClassNotFoundException e) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
