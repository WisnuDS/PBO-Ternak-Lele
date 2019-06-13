package ternak.lele.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;

import javax.swing.*;
import java.net.URL;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PenjualanController implements Initializable {

    @FXML
    private JFXTextField jumlahIkanField;

    @FXML
    private JFXComboBox<Integer> kolamBox;

    @FXML
    private JFXTextField hargaIkanField;

    @FXML
    private Label hargaLabel;

    @FXML
    private JFXButton doneButton;

    @FXML
    void penjualanIkanAction(ActionEvent event) {
        boolean status = penjualanBarang(jumlahIkanField, hargaIkanField);
        if (status) {
            int jumlah = 0;
            try {
                ResultSet resultSet = DBHelper.selectColumn("kolams", new String[]{"jumlah_lele"}, "id = " + kolamBox.getValue());
                resultSet.next();
                jumlah = resultSet.getInt("jumlah_lele") - Integer.parseInt(jumlahIkanField.getText());
            } catch (Exception e) {
                String message = "Terjadi error";
                JOptionPane.showMessageDialog(null, message);
                return;
            }
            Map<String, String> params = new HashMap<String, String>();
            params.put("jumlah_lele", jumlah + "");
            DBHelper.update("kolams", params, "id = " + kolamBox.getValue());

            String message = "Data berhasil dimasukan";
            JOptionPane.showMessageDialog(null, message);
            jumlahIkanField.setText("");
            hargaIkanField.setText("");
            hargaLabel.setText("Rp -");
        } else {
            String message = "Data gagal dimasukan";
            JOptionPane.showMessageDialog(null, message);
        }
    }

    @FXML
    void totalChangeAction(KeyEvent event) {
        if (!hargaIkanField.getText().equals("") && GeneralHelper.isNumeric(hargaIkanField.getText()) && !jumlahIkanField.getText().equals("") && GeneralHelper.isNumeric(jumlahIkanField.getText())) {
            int a = Integer.parseInt(hargaIkanField.getText());
            int b = Integer.parseInt(jumlahIkanField.getText());
            hargaLabel.setText("Rp " + (a * b));
        } else {
            hargaLabel.setText("Rp -");
        }
    }

    private boolean penjualanBarang(JFXTextField jumlahField, JFXTextField hargaField) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("jumlah", jumlahField.getText());
        params.put("harga", hargaField.getText());
        boolean status = DBHelper.insert("penjualan", params);

        return status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> data = FXCollections.observableArrayList(1,2,3);
        kolamBox.setItems(data);
        kolamBox.setValue(1);
    }
}
