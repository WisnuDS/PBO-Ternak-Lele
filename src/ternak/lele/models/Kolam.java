package ternak.lele.models;

import ternak.lele.controllers.LoginController;
import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Kolam extends LoginController {

    public static final String TABLE = "kolams";

    private int idKolam;
    private int umur;
    private int jumlahLele;

    public Kolam(int idKolam, int umur, int jumlahLele) {
        this.idKolam = idKolam;
        this.umur = umur;
        this.jumlahLele = jumlahLele;
    }

    public Kolam() {
    }

    public static Kolam getKolamById(int id) {
        ResultSet resultSet = DBHelper.selectAll(TABLE, String.format("id = %d", id));
        System.out.println(GeneralHelper.resultSetToString(resultSet));
        Kolam kolam;

        try {
            int id_ = resultSet.getInt("id");
            int umur_ = resultSet.getInt("umur");
            int jumlahLele_ = resultSet.getInt("jumlah_lele");
            kolam = new Kolam(id_, umur_, jumlahLele_);
        } catch (Exception e) {
            e.printStackTrace();
            kolam = null;
        }

        return kolam;
    }

    public static ArrayList<Kolam> getAllKolam() {
        ArrayList<Kolam> kolams = new ArrayList<Kolam>();
        ResultSet resultSet = DBHelper.selectAll(TABLE);
        try {
            while (resultSet.next()) {
                int id_ = resultSet.getInt("id");
                int umur_ = resultSet.getInt("umur");
                int jumlahLele_ = resultSet.getInt("jumlah_lele");
                Kolam kolam = new Kolam(id_, umur_, jumlahLele_);
                kolams.add(kolam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kolams;
    }

    public int getIdKolam() {
        return idKolam;
    }

    public void setIdKolam(int idKolam) {
        this.idKolam = idKolam;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public int getJumlahLele() {
        return jumlahLele;
    }

    public void setJumlahLele(int jumlahLele) {
        this.jumlahLele = jumlahLele;
    }
}
