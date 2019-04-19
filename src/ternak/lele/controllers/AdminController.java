/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ternak.lele.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fian
 */
public class AdminController implements Initializable {

    @FXML
    private BorderPane mainParent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void changeDashboardPanel(ActionEvent event){
        System.out.println("CHANGE DASHBOARD");
        loadPanel("Dashboard");
    }

    @FXML
    private void changePembelianPanel(ActionEvent event){
        loadPanel("Pembelian");
    }

    @FXML
    private void changePemeliharaanPanel(ActionEvent event){
        loadPanel("Pemeliharaan");
    }

    @FXML
    private void changePenjualanPanel(ActionEvent event){
        loadPanel("Penjualan");
    }

    private void loadPanel(String panel){
        panel = "../views/pemilik/" + panel + ".fxml";
        System.out.println(panel);

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(panel));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainParent.setCenter(root);
    }
    
}
