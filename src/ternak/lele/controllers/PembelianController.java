package ternak.lele.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;
import ternak.lele.models.Penjualan;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PembelianController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private JFXTextField jumlahField;

    @FXML
    private JFXTextField hargaField;

    @FXML
    private Label totalLabel;

    @FXML
    private JFXButton buttonDoneBibit;

    @FXML
    private JFXTextField jumlahPakanField;

    @FXML
    private JFXTextField hargaPakanField;

    @FXML
    private JFXTextField jumlahObatField;

    @FXML
    private JFXTextField hargaObatField;

    @FXML
    private Label totalHargaField;

    @FXML
    private JFXButton buttonDonePakan;

    @FXML
    void updateTotalPembelianBibit(KeyEvent event) {
        if (!hargaField.getText().equals("") && GeneralHelper.isNumeric(hargaField.getText()) && !jumlahField.getText().equals("") && GeneralHelper.isNumeric(jumlahField.getText())) {
            int a = Integer.parseInt(hargaField.getText());
            int b = Integer.parseInt(jumlahField.getText());
            totalLabel.setText("Rp " + (a * b));
        } else {
            totalLabel.setText("Rp -");
        }
    }

    @FXML
    void pembelianBibitAction(ActionEvent event) {
        boolean status = pembelianBarang(jumlahField, hargaField, "'Bibit'");
        if (status) {
            int jumlah = 0;
            try {
                ResultSet resultSet = DBHelper.selectColumn("kolams", new String[]{"jumlah_lele"}, "id = 1");
                resultSet.next();
                jumlah = resultSet.getInt("jumlah_lele") + Integer.parseInt(jumlahField.getText());
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
            jumlahField.setText("");
            hargaField.setText("");
            totalLabel.setText("Rp -");
        } else {
            String message = "Data gagal dimasukan";
            JOptionPane.showMessageDialog(null, message);
        }

    }

    @FXML
    void pembelianPakanAction(ActionEvent event) {
        boolean status1 = true;
        boolean status2 = true;

        if (!jumlahPakanField.getText().equals("") && !hargaPakanField.getText().equals("") && GeneralHelper.isNumeric(jumlahPakanField.getText()) && GeneralHelper.isNumeric(hargaPakanField.getText())) {
            status1 = pembelianBarang(jumlahPakanField, hargaPakanField, "'Pakan'");
        }

        if (!jumlahObatField.getText().equals("") && !hargaObatField.getText().equals("") && GeneralHelper.isNumeric(jumlahObatField.getText()) && GeneralHelper.isNumeric(hargaObatField.getText())) {
            status2 = pembelianBarang(jumlahObatField, hargaObatField, "'Obat'");
        }

        if (status1 || status2) {
            String message = "Data berhasil dimasukan";
            JOptionPane.showMessageDialog(null, message);
            jumlahPakanField.setText("");
            hargaPakanField.setText("");
            jumlahObatField.setText("");
            hargaObatField.setText("");
            totalLabel.setText("Rp -");
        } else {
            String message = "Data gagal dimasukan";
            JOptionPane.showMessageDialog(null, message);
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

    @FXML
    void updateTotalPembelianPakan(KeyEvent event) {
        if (!jumlahPakanField.getText().equals("") && GeneralHelper.isNumeric(jumlahPakanField.getText()) && !hargaPakanField.getText().equals("") && GeneralHelper.isNumeric(hargaPakanField.getText())) {
            int a = Integer.parseInt(jumlahPakanField.getText());
            int b = Integer.parseInt(hargaPakanField.getText());
            int total = (a * b);

            if (!jumlahObatField.getText().equals("") && GeneralHelper.isNumeric(jumlahObatField.getText()) && !hargaObatField.getText().equals("") && GeneralHelper.isNumeric(hargaObatField.getText())) {
                int x = Integer.parseInt(jumlahObatField.getText());
                int y = Integer.parseInt(hargaObatField.getText());
                total += (x * y);
            }
            totalHargaField.setText("Rp " + total);
        } else {
            totalHargaField.setText("Rp -");
        }
    }

    @FXML
    private JFXButton buttonExport;

    @FXML
    void exportData(ActionEvent event) {
        String filename = "Data Penjualan.csv";
        try {
            FileWriter fw = new FileWriter(filename);
            fw.append("No,Tanggal,Jenis Barang, Jumlah Barang, Harga per Unit, Total Harga\n");
            ResultSet resultSet = DBHelper.selectAll("pembelian");
            while (resultSet.next()) {
                fw.append(resultSet.getInt("id") + ",");
                fw.append(resultSet.getDate("tanggal").toString() + ",");
                fw.append(resultSet.getString("barang") + ",");
                fw.append(resultSet.getInt("jumlah") + ",");
                fw.append(resultSet.getInt("harga_unit") + ",");
                fw.append((resultSet.getInt("harga_unit") * resultSet.getInt("jumlah")) + "");
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            JOptionPane.showMessageDialog(null, "Data berhasil diexport");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
