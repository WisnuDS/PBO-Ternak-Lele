/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ternak.lele.controllers;

import java.io.IOException;
import java.net.URL;
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

/**
 *
 * @author fian
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;
    
    @FXML
    private void loginOnClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(username.equals("pemilik")){
            changePage(event, "pemilik");
        } else {
            changePage(event, "peternak");
        }
    }

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
    }    
    
}
