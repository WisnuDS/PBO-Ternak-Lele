package ternak.lele.models;

import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;

import java.sql.*;

public class User {

    public static final String TABLE = "users";

    private int idUser;
    private String nama;
    private String username;
    private String password;

    private int idLevel;
    private String level;

    public User(int idUser, String nama, String username, String password, int idLevel, String level) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.idLevel = idLevel;
        this.level = level;
    }

    public static int getLoginValue(String username, String password) {
        username = username.replaceAll("'", "").replaceAll(" ", "");
        password = password.replaceAll("'", "").replaceAll(" ", "");
        ResultSet resultSet = DBHelper.selectAll(TABLE, String.format("username = '%s' and password = '%s'", username, password));
        try {
            if (!resultSet.next()) {
                return 0;
            } else {
                return resultSet.getInt("id_level");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static User getUser(int id) {
        ResultSet resultSet = DBHelper.selectAll(TABLE, String.format("users.id = %d", id), "levels", "id_level");
        User user;
        try {
            String nama_ = resultSet.getString("nama");
            String username_ = resultSet.getString("username");
            String password_ = resultSet.getString("password");
            int idLevel_ = resultSet.getInt("id_level");
            int id_ = resultSet.getInt("id");
            String level_ = resultSet.getString("level");
            user = new User(id_, nama_, username_, password_, idLevel_, level_);
        } catch (Exception e) {
            user = null;
        }

        return user;
    }

    public static int getLoginValue(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
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
