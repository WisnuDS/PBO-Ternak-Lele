/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ternak.lele.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author fian
 */
public class PeternakController implements Initializable {

    @FXML
    private BorderPane mainParent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPanel("Dashboard");
    }

    @FXML
    private void changeDashboardPanel(ActionEvent event) {
        loadPanel("Dashboard");
    }

    @FXML
    private void changePemeliharaanPanel(ActionEvent event) {
        loadPanel("Pemeliharaan");
    }

    @FXML
    private void logout(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadPanel(String panel) {
        panel = "./../views/peternak/" + panel + ".fxml";

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(panel));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainParent.setCenter(root);
    }

}
