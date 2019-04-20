/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ternak.lele.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ternak.lele.helpers.DBConnection;
import ternak.lele.models.User;

/**
 *
 * @author fian
 */
public class LoginController implements Initializable {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;
    
    @FXML
    private void loginOnClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

//        int aktor = getLoginAktor(username, password);

        int aktor = User.getLoginValue(connection, preparedStatement, resultSet, username, password);

        System.out.println(aktor);

        if(aktor == 1){
            changePage(event, "pemilik");
        } else if (aktor == 2){
            changePage(event, "peternak");
        } else {
            System.out.println("Username dan password salah");
        }

//        if(username.equals("pemilik")){
//            changePage(event, "pemilik");
//        } else {
//            changePage(event, "peternak");
//        }
    }

//    private int getLoginAktor(String username, String password){
//        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
//
//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//
//            resultSet = preparedStatement.executeQuery();
//
//            if(!resultSet.next()){
//                return 0;
//            } else {
//                return resultSet.getInt("id_level");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return 0;
//    }

    private void changePage(ActionEvent event, String aktor){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/" + aktor + "/Main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connection = DBConnection.connectDB();
    }
    
}
