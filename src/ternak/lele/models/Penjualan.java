package ternak.lele.models;

import ternak.lele.helpers.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class Penjualan {

    public static final String TABLE = "penjualan";

    private int jumlah;
    private int harga;
    private Date tanggal;

    public Penjualan(int jumlah, int harga, Date tanggal) {
        this.jumlah = jumlah;
        this.harga = harga;
        this.tanggal = tanggal;
    }

    public static ArrayList<Penjualan> getAllPenjualan(){
        ArrayList<Penjualan> penjualans = new ArrayList<Penjualan>();
        ResultSet resultSet = DBHelper.selectAll(TABLE);
        try {
            while (resultSet.next()){
                int jumlah_ = resultSet.getInt("jumlah");
                int harga_ = resultSet.getInt("harga");
                Date tanggal_ = resultSet.getDate("tanggal");
                penjualans.add(new Penjualan(jumlah_, harga_, tanggal_));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return penjualans;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
}
