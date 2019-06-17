package ternak.lele.controllers;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;
import sun.security.util.Pem;
import ternak.lele.models.Kolam;
import ternak.lele.models.Pembelian;
import ternak.lele.models.Pemeliharaan;
import ternak.lele.models.Penjualan;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int kolam1 = Kolam.getKolamById(1).getJumlahLele();
        int kolam2 = Kolam.getKolamById(2).getJumlahLele();
        int kolam3 = Kolam.getKolamById(3).getJumlahLele();
        int jmlIkan = kolam1 + kolam2 + kolam3;
        int ikanMati = Pemeliharaan.getAllIkanMati();
        int jmlPakan = Pembelian.getPakan() - Pemeliharaan.getAllPakan();

        ikanKolam1.setText(kolam1 + " ekor");
        ikanKolam2.setText(kolam2 + " ekor");
        ikanKolam3.setText(kolam3 + " ekor");
        totalKolam.setText(jmlIkan + " ekor");
        totalIkanMati.setText(ikanMati + " ekor");
        jumlahPakan.setText(jmlPakan + " kg");

        initTable();

    }

    private void initTable() {
        ArrayList<Pembelian> pembelians = Pembelian.getAllPembelian();
        ArrayList<Penjualan> penjualans = Penjualan.getAllPenjualan();

        noColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<LogObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<LogObject, String> param) {
                return param.getValue().getValue().no;
            }
        });
        tanggalColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<LogObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<LogObject, String> param) {
                return param.getValue().getValue().date;
            }
        });
        jenisColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<LogObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<LogObject, String> param) {
                return param.getValue().getValue().jenis;
            }
        });
        hargaColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<LogObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<LogObject, String> param) {
                return param.getValue().getValue().harga;
            }
        });
        jumlahColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<LogObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<LogObject, String> param) {
                return param.getValue().getValue().jumlah;
            }
        });
        totalColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<LogObject, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<LogObject, String> param) {
                return param.getValue().getValue().total;
            }
        });

        ObservableList<LogObject> data = FXCollections.observableArrayList();
        int index = 1;
        for (Pembelian pembelian : pembelians) {
            String nomor = index++ + "";
            String tanggal = pembelian.getDate().toString();
            String jenisBarang = pembelian.getBarang();
            String harga = pembelian.getHargaUnit() + "";
            String jumlah = pembelian.getJumlah() + "";
            String total = pembelian.getHargaUnit() * pembelian.getJumlah() + "";
            data.add(new LogObject(nomor, tanggal, jenisBarang, harga, jumlah, total));
        }

        for(Penjualan penjualan : penjualans){
            String nomor = index++ + "";
            String tanggal = penjualan.getTanggal().toString();
            String jenisBarang = "Ikan (Jual)";
            String harga = penjualan.getHarga() + "";
            String jumlah = penjualan.getJumlah() + "";
            String total = penjualan.getHarga() * penjualan.getJumlah() + "";
            data.add(new LogObject(nomor, tanggal, jenisBarang, harga, jumlah, total));
        }

        tableLog.getColumns().setAll(noColumn, tanggalColumn, jenisColumn, hargaColumn, jumlahColumn, totalColumn);

        TreeItem<LogObject> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        tableLog.setRoot(root);
        tableLog.setShowRoot(false);
    }

    @FXML
    private JFXTreeTableView<LogObject> tableLog;

    @FXML
    private TreeTableColumn<LogObject, String> noColumn;

    @FXML
    private TreeTableColumn<LogObject, String> tanggalColumn;

    @FXML
    private TreeTableColumn<LogObject, String> jenisColumn;

    @FXML
    private TreeTableColumn<LogObject, String> hargaColumn;

    @FXML
    private TreeTableColumn<LogObject, String> jumlahColumn;

    @FXML
    private TreeTableColumn<LogObject, String> totalColumn;

    @FXML
    private Label ikanKolam1;

    @FXML
    private Label ikanKolam2;

    @FXML
    private Label ikanKolam3;

    @FXML
    private Label totalKolam;

    @FXML
    private Label totalIkanMati;

    @FXML
    private Label jumlahPakan;

    class LogObject extends RecursiveTreeObject<LogObject> {
        StringProperty no;
        StringProperty date;
        StringProperty jenis;
        StringProperty harga;
        StringProperty jumlah;
        StringProperty total;

        public LogObject(String no, String date, String jenis, String harga, String jumlah, String total) {
            this.no = new SimpleStringProperty(no);
            this.date = new SimpleStringProperty(date);
            this.jenis = new SimpleStringProperty(jenis);
            this.harga = new SimpleStringProperty(harga);
            this.jumlah = new SimpleStringProperty(jumlah);
            this.total = new SimpleStringProperty(total);
        }
    }

}
