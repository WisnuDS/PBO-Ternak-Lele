package ternak.lele.models;

import ternak.lele.helpers.DBHelper;

import java.sql.*;

public class User{
    private int idUser;
    private String username;
    private String password;

    private int idLevel;
    private String level;

    public static int getLoginValue(String username, String password){
        username = username.replaceAll("'", "").replaceAll(" ", "");
        password = password.replaceAll("'", "").replaceAll(" ", "");
        ResultSet resultSet = DBHelper.selectAll("users", String.format("username = '%s' and password = '%s'", username, password));
        try {
            if(!resultSet.next()){
                return 0;
            } else {
                return resultSet.getInt("id_level");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
