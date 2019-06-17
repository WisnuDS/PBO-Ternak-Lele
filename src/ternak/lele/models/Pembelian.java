package ternak.lele.models;

import ternak.lele.helpers.DBHelper;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Pembelian {

    public static final String TABLE = "pembelian";

    private int jumlah;
    private int hargaUnit;
    private String barang;
    private Date date;

    public Pembelian() {
    }

    public Pembelian(int jumlah, int hargaUnit, String barang, Date date) {
        this.jumlah = jumlah;
        this.hargaUnit = hargaUnit;
        this.barang = barang;
        this.date = date;
    }

    public static ArrayList<Pembelian> getAllPembelian(){
        ResultSet resultSet = DBHelper.selectAll(TABLE);
        ArrayList<Pembelian> result = new ArrayList<Pembelian>();
        try {
            while (resultSet.next()){
                Pembelian pembelian = new Pembelian();
                pembelian.setJumlah(resultSet.getInt("jumlah"));
                pembelian.setHargaUnit(resultSet.getInt("harga_unit"));
                pembelian.setBarang(resultSet.getString("barang") + " (Beli)");
                pembelian.setDate(resultSet.getDate("tanggal"));
                result.add(pembelian);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static int getPakan(){
        ResultSet resultSet = DBHelper.selectColumn(TABLE, new String[]{"jumlah"}, "barang = 'Pakan'");
        int pakan = 0;
        try {
            while (resultSet.next()){
                pakan += resultSet.getInt("jumlah");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return pakan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHargaUnit() {
        return hargaUnit;
    }

    public void setHargaUnit(int hargaUnit) {
        this.hargaUnit = hargaUnit;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
