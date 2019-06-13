package ternak.lele.models;

import org.json.JSONObject;
import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;

import java.sql.ResultSet;

public class Pemeliharaan {

    public static final String TABLE = "pemeliharaans";

    private int id;
    private int idKolam;
    private int hari;
    private boolean[] pemberianMakan;
    private boolean[] pemberianObat;
    private int ikanMati;
    private boolean pembersihan;

    public Pemeliharaan() {
    }

    public Pemeliharaan(int id, int idKolam, int hari, boolean[] pemberianMakan, boolean[] pemberianObat, int ikanMati, boolean pembersihan) {
        this.id = id;
        this.idKolam = idKolam;
        this.hari = hari;
        this.pemberianMakan = pemberianMakan;
        this.pemberianObat = pemberianObat;
        this.ikanMati = ikanMati;
        this.pembersihan = pembersihan;
    }

    public static Pemeliharaan getPemeliharaanById(int id){
        Pemeliharaan pemeliharaan = null;
        ResultSet resultSet = DBHelper.selectAll(TABLE, String.format("id = %d", id));
        try {
            if(resultSet.next()){
                JSONObject data = GeneralHelper.resultSetToJson(resultSet);
                int id_ = resultSet.getInt("id");
                int idKolam_ = resultSet.getInt("id_kolam");
                int hari_ = resultSet.getInt("hari");
                boolean[] pemberianMakan_ = GeneralHelper.getBooleanArrayFromJson(data, "pemberian_makan");
                boolean[] pemberianObat_ = GeneralHelper.getBooleanArrayFromJson(data, "pemberian_obat");
                int ikanMati_ = resultSet.getInt("ikan_mati");
                boolean pembersihan_ = resultSet.getBoolean("pembersihan");
                pemeliharaan = new Pemeliharaan(id_, idKolam_, hari_, pemberianMakan_, pemberianObat_, ikanMati_, pembersihan_);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return pemeliharaan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKolam() {
        return idKolam;
    }

    public void setIdKolam(int idKolam) {
        this.idKolam = idKolam;
    }

    public int getHari() {
        return hari;
    }

    public void setHari(int hari) {
        this.hari = hari;
    }

    public boolean[] getPemberianMakan() {
        return pemberianMakan;
    }

    public void setPemberianMakan(boolean[] pemberianMakan) {
        this.pemberianMakan = pemberianMakan;
    }

    public boolean[] getPemberianObat() {
        return pemberianObat;
    }

    public void setPemberianObat(boolean[] pemberianObat) {
        this.pemberianObat = pemberianObat;
    }

    public int getIkanMati() {
        return ikanMati;
    }

    public void setIkanMati(int ikanMati) {
        this.ikanMati = ikanMati;
    }

    public boolean isPembersihan() {
        return pembersihan;
    }

    public void setPembersihan(boolean pembersihan) {
        this.pembersihan = pembersihan;
    }
}
