package ternak.lele.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Level{
    private String username;
    private String password;

    public User(int idLevel, String level) {
        super(idLevel, level);
    }

    public static int getLoginValue(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, String username, String password){
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                return 0;
            } else {
                return resultSet.getInt("id_level");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
