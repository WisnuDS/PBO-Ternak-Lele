package ternak.lele.controllers;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import ternak.lele.helpers.DBHelper;
import ternak.lele.models.Pemeliharaan;

import javax.swing.*;
import java.net.URL;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PemeliharaanController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        YearMonth yearMonthObject = YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        ObservableList<Integer> data = FXCollections.observableArrayList();
        for(int i=1; i<=daysInMonth; i++){
            data.add(i);
        }
        hariBox1.setItems(data);
        hariBox1.setValue(1);
        hariBox2.setItems(data);
        hariBox2.setValue(1);
        hariBox3.setItems(data);
        hariBox3.setValue(1);
    }

    @FXML
    private JFXButton lihatButton1;

    @FXML
    private JFXCheckBox mPagi1;

    @FXML
    private JFXCheckBox mSiang1;

    @FXML
    private JFXCheckBox mSore1;

    @FXML
    private JFXCheckBox mMalam1;

    @FXML
    private JFXCheckBox oPagi1;

    @FXML
    private JFXCheckBox oSiang1;

    @FXML
    private JFXCheckBox oSore1;

    @FXML
    private JFXCheckBox oMalam1;

    @FXML
    private JFXTextField matiField1;

    @FXML
    private JFXCheckBox bersihKolam1;

    @FXML
    private JFXButton submit1;

    @FXML
    private JFXComboBox<Integer> hariBox1;

    @FXML
    private JFXComboBox<Integer> hariBox2;

    @FXML
    private JFXComboBox<Integer> hariBox3;

    @FXML
    void getDataAction1(ActionEvent event) {
        int hari = hariBox1.getValue();
        Pemeliharaan pemeliharaan = Pemeliharaan.getPemeliharaanByHari(1, hari);
        if(pemeliharaan != null){
            matiField1.setText(pemeliharaan.getIkanMati() + "");
            mPagi1.setSelected(pemeliharaan.getPemberianMakan()[0]);
            mSiang1.setSelected(pemeliharaan.getPemberianMakan()[1]);
            mSore1.setSelected(pemeliharaan.getPemberianMakan()[2]);
            mMalam1.setSelected(pemeliharaan.getPemberianMakan()[3]);

            oPagi1.setSelected(pemeliharaan.getPemberianObat()[0]);
            oSiang1.setSelected(pemeliharaan.getPemberianObat()[1]);
            oSore1.setSelected(pemeliharaan.getPemberianObat()[2]);
            oMalam1.setSelected(pemeliharaan.getPemberianObat()[3]);

            bersihKolam1.setSelected(pemeliharaan.isPembersihan());
        } else {
            matiField1.setText("");
            mPagi1.setSelected(false);
            mSiang1.setSelected(false);
            mSore1.setSelected(false);
            mMalam1.setSelected(false);

            oPagi1.setSelected(false);
            oSiang1.setSelected(false);
            oSore1.setSelected(false);
            oMalam1.setSelected(false);

            bersihKolam1.setSelected(false);
        }
    }

    @FXML
    void submitDataAction(ActionEvent event){
        int hari = hariBox1.getValue();
        Pemeliharaan pemeliharaan = Pemeliharaan.getPemeliharaanByHari(1, hari);
        if(pemeliharaan != null){
            boolean[] makans = new boolean[]{mPagi1.isSelected(), mSiang1.isSelected(), mSore1.isSelected(), mMalam1.isSelected()};
            boolean[] obats = new boolean[]{oPagi1.isSelected(), oSiang1.isSelected(), oSore1.isSelected(), oMalam1.isSelected()};
            boolean status = Pemeliharaan.updateDataPemeliharaan(
                    1,
                    hari,
                    makans,
                    obats,
                    Integer.parseInt(matiField1.getText()),
                    bersihKolam1.isSelected());
            if(status){
                JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diubah");
            }
        } else {
            boolean[] makans = new boolean[]{mPagi1.isSelected(), mSiang1.isSelected(), mSore1.isSelected(), mMalam1.isSelected()};
            boolean[] obats = new boolean[]{oPagi1.isSelected(), oSiang1.isSelected(), oSore1.isSelected(), oMalam1.isSelected()};
            boolean status = Pemeliharaan.createDataPemeliharaan(
                    1,
                    hari,
                    makans,
                    obats,
                    Integer.parseInt(matiField1.getText()),
                    bersihKolam1.isSelected());
            if(status){
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal ditambahkan");
            }
        }
    }

    @FXML
    void getDataAction2(ActionEvent event) {
        System.out.println("2");
    }

    @FXML
    void getDataAction3(ActionEvent event) {
        System.out.println("3");
    }
}
