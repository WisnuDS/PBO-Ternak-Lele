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
        return getResultSet(sql);
    }

    public static ResultSet selectAll(String table, String joinTable, String foreignKey){
        String sql = String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.id", table, joinTable, table, foreignKey, joinTable);
        return getResultSet(sql);
    }

    public static ResultSet selectAll(String table, String requirment, String joinTable, String foreignKey){
        String sql = String.format("SELECT * FROM %s JOIN %s ON %s.%s = %s.id WHERE %s", table, joinTable, table, foreignKey, joinTable, requirment);
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
        System.out.println(sql);
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
