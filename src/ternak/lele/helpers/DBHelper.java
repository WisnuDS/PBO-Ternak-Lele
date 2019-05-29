package ternak.lele.helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    public static ResultSet selectAll(String table){
        String sql = String.format("SELECT * FROM %s", table);
        return getResultSet(sql);
    }

    public static ResultSet selectAll(String table, String requirment){
        String sql = String.format("SELECT * FROM %s WHERE %s", table, requirment);
        System.out.println(sql);
        return getResultSet(sql);
    }

    public static ResultSet selectColumn(String table, String[] columns){
        String col = "";
        for(String s : columns){
            col += s + ", ";
        }
        String sql = String.format("SELECT %s FROM %s", col, table);
        return getResultSet(sql);
    }

    public static ResultSet selectColumn(String table, String[] columns, String requirment){
        String col = "";
        for(String s : columns){
            col += s + ", ";
        }
        String sql = String.format("SELECT %s FROM %s WHERE %s", col, table, requirment);
        return getResultSet(sql);
    }

    private static ResultSet getResultSet(String sql) {
        ResultSet resultSet = null;
        try {
            Statement statement = Config.connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
