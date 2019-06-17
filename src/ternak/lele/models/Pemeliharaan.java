package ternak.lele.models;

import org.json.JSONObject;
import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Pemeliharaan {

    public static final String TABLE = "pemeliharaans";
    public static final int PAKAN = 10;
    public static final int OBAT = 5;

    private int id;
    private int idKolam;
    private int hari;
    private boolean[] pemberianMakan;
    private boolean[] pemberianObat;
    private int ikanMati;
    private boolean pembersihan;
    private int totalPakan;
    private int totalObat;

    public Pemeliharaan() {
    }

    public Pemeliharaan(int id, int idKolam, int hari, boolean[] pemberianMakan, boolean[] pemberianObat, int ikanMati, boolean pembersihan, int totalPakan, int totalObat) {
        this.id = id;
        this.idKolam = idKolam;
        this.hari = hari;
        this.pemberianMakan = pemberianMakan;
        this.pemberianObat = pemberianObat;
        this.ikanMati = ikanMati;
        this.pembersihan = pembersihan;
        this.totalPakan = totalPakan;
        this.totalObat = totalObat;
    }

    public static Pemeliharaan getPemeliharaanById(int id) {
        Pemeliharaan pemeliharaan = null;
        ResultSet resultSet = DBHelper.selectAll(TABLE, String.format("id = %d", id));
        try {
            if (resultSet.next()) {
                int id_ = resultSet.getInt("id");
                int idKolam_ = resultSet.getInt("id_kolam");
                int hari_ = resultSet.getInt("hari");
                int ikanMati_ = resultSet.getInt("ikan_mati");
                boolean pembersihan_ = resultSet.getBoolean("pembersihan");
                boolean[] pemberianMakan_ = GeneralHelper.stringToBooleanArray(resultSet.getString("pemberian_makan"));
                boolean[] pemberianObat_ = GeneralHelper.stringToBooleanArray(resultSet.getString("pemberian_obat"));
                int totalPakan_ = resultSet.getInt("total_pakan");
                int totalObat_ = resultSet.getInt("total_obat");
                pemeliharaan = new Pemeliharaan(id_, idKolam_, hari_, pemberianMakan_, pemberianObat_, ikanMati_, pembersihan_, totalPakan_, totalObat_);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pemeliharaan;
    }

    public static Pemeliharaan getPemeliharaanByHari(int kolam, int hari) {
        Pemeliharaan pemeliharaan = null;
        ResultSet resultSet = DBHelper.selectAll(TABLE, String.format("id_kolam = %d AND hari = %s", kolam, hari));
        try {
            if (resultSet.next()) {
                int id_ = resultSet.getInt("id");
                int idKolam_ = resultSet.getInt("id_kolam");
                int hari_ = resultSet.getInt("hari");
                boolean[] pemberianMakan_ = GeneralHelper.stringToBooleanArray(resultSet.getString("pemberian_makan"));
                boolean[] pemberianObat_ = GeneralHelper.stringToBooleanArray(resultSet.getString("pemberian_obat"));
                int ikanMati_ = resultSet.getInt("ikan_mati");
                boolean pembersihan_ = resultSet.getBoolean("pembersihan");
                int totalPakan_ = resultSet.getInt("total_pakan");
                int totalObat_ = resultSet.getInt("total_obat");
                pemeliharaan = new Pemeliharaan(id_, idKolam_, hari_, pemberianMakan_, pemberianObat_, ikanMati_, pembersihan_, totalPakan_, totalObat_);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pemeliharaan;
    }

    public static boolean createDataPemeliharaan(int kolam, int hari, boolean[] makan, boolean[] obat, int jumlahMati, boolean pembersihan) {
        int pakan_ = 0;
        int obat_ = 0;
        String makans = "'[";
        for (boolean m : makan) {
            pakan_ += m ? PAKAN : 0;
            makans += m ? "1" : "0";
            makans += ",";
        }
        makans = makans.substring(0, makans.length() - 1);
        makans += "]'";

        String obats = "'[";
        for (boolean o : obat) {
            obat_ += o ? OBAT : 0;
            obats += o ? "1" : "0";
            obats += ",";
        }
        obats = obats.substring(0, obats.length() - 1);
        obats += "]'";

        int jumlah = 0;
        try {
            ResultSet resultSet = DBHelper.selectColumn("kolams", new String[]{"jumlah_lele"}, "id = 1");
            resultSet.next();
            jumlah = resultSet.getInt("jumlah_lele") - jumlahMati;
        } catch (Exception e) {
            return false;
        }
        Map<String, String> params1 = new HashMap<String, String>();
        params1.put("jumlah_lele", jumlah + "");
        DBHelper.update("kolams", params1, "id = " + kolam);

        Map<String, String> params = new HashMap<String, String>();
        params.put("id_kolam", kolam + "");
        params.put("hari", hari + "");
        params.put("pemberian_makan", makans);
        params.put("pemberian_obat", obats);
        params.put("ikan_mati", jumlahMati + "");
        params.put("pembersihan", pembersihan ? "1" : "0");
        params.put("total_pakan", pakan_ + "");
        params.put("total_obat", obat_ + "");
        return DBHelper.insert("pemeliharaans", params);
    }

    public static boolean updateDataPemeliharaan(int kolam, int hari, boolean[] makan, boolean[] obat, int jumlahMati, boolean pembersihan) {
        String makans = "'[";
        for (boolean m : makan) {
            makans += m ? "1" : "0";
            makans += ",";
        }
        makans = makans.substring(0, makans.length() - 1);
        makans += "]'";

        String obats = "'[";
        for (boolean o : obat) {
            obats += o ? "1" : "0";
            obats += ",";
        }
        obats = obats.substring(0, obats.length() - 1);
        obats += "]'";

        int jumlah = 0;
        try {
            ResultSet resultSet = DBHelper.selectColumn("kolams", new String[]{"jumlah_lele"}, "id = 1");
            resultSet.next();
            jumlah = resultSet.getInt("jumlah_lele") - jumlahMati;
        } catch (Exception e) {
            return false;
        }
        Map<String, String> params1 = new HashMap<String, String>();
        params1.put("jumlah_lele", jumlah + "");
        DBHelper.update("kolams", params1, "id = " + kolam);

        Map<String, String> params = new HashMap<String, String>();
        params.put("id_kolam", kolam + "");
        params.put("hari", hari + "");
        params.put("pemberian_makan", makans);
        params.put("pemberian_obat", obats);
        params.put("ikan_mati", jumlahMati + "");
        params.put("pembersihan", pembersihan ? "1" : "0");
        return DBHelper.update("pemeliharaans", params, String.format("id_kolam = %s AND hari = %s", kolam, hari));
    }

    public static int getAllPakan() {
        ResultSet resultSet = DBHelper.selectColumn(TABLE, new String[]{"total_pakan"});
        int pakan = 0;
        try {
            while (resultSet.next()) {
                pakan += resultSet.getInt("total_pakan");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pakan;
    }

    public static int getAllObat() {
        ResultSet resultSet = DBHelper.selectColumn(TABLE, new String[]{"total_obat"});
        int obat = 0;
        try {
            while (resultSet.next()) {
                obat += resultSet.getInt("total_obat");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obat;
    }

    public static int getAllIkanMati() {
        ResultSet resultSet = DBHelper.selectColumn(TABLE, new String[]{"ikan_mati"});
        int ikan = 0;
        try {
            while (resultSet.next()) {
                ikan += resultSet.getInt("ikan_mati");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ikan;
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

    public int getTotalPakan() {
        return totalPakan;
    }

    public void setTotalPakan(int totalPakan) {
        this.totalPakan = totalPakan;
    }

    public int getTotalObat() {
        return totalObat;
    }

    public void setTotalObat(int totalObat) {
        this.totalObat = totalObat;
    }
}
