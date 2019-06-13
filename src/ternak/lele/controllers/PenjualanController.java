package ternak.lele.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class PenjualanController {

    @FXML
    private JFXTextField jumlahIkanField;

    @FXML
    private JFXTextField hargaIkanField;

    @FXML
    private Label hargaLabel;

    @FXML
    private JFXButton doneButton;

    @FXML
    void penjualanIkanAction(ActionEvent event) {
        boolean status = pembelianBarang(jumlahIkanField, hargaIkanField, "'Bibit'");
        if (status) {
            int jumlah = 0;
            try {
                ResultSet resultSet = DBHelper.selectColumn("kolams", new String[]{"jumlah_lele"}, "id = 1");
                resultSet.next();
                jumlah = resultSet.getInt("jumlah_lele") - Integer.parseInt(jumlahIkanField.getText());
            } catch (Exception e) {
                String message = "Terjadi error";
                JOptionPane.showMessageDialog(null, message);
                return;
            }
            Map<String, String> params = new HashMap<String, String>();
            params.put("jumlah_lele", jumlah + "");
            DBHelper.update("kolams", params, "id = 1");


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

    private boolean pembelianBarang(JFXTextField jumlahField, JFXTextField hargaField, String category) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("jumlah", jumlahField.getText());
        params.put("harga_unit", hargaField.getText());
        params.put("barang", category);
        boolean status = DBHelper.insert("pembelian", params);

        return status;
    }

}
