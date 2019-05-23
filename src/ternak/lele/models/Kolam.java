package ternak.lele.models;

import ternak.lele.controllers.LoginController;

public class Kolam extends LoginController {
    private int idKolam;
    private int hari;
    private boolean[] pemberianMakan;
    private boolean[] pemberianObat;
    private int jumlahIkanMati;
    private boolean pembersihan;

    public Kolam(int idKolam, int hari, boolean[] pemberianMakan, boolean[] pemberianObat, int jumlahIkanMati, boolean pembersihan) {
        this.idKolam = idKolam;
        this.hari = hari;
        this.pemberianMakan = pemberianMakan;
        this.pemberianObat = pemberianObat;
        this.jumlahIkanMati = jumlahIkanMati;
        this.pembersihan = pembersihan;
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

    public int getJumlahIkanMati() {
        return jumlahIkanMati;
    }

    public void setJumlahIkanMati(int jumlahIkanMati) {
        this.jumlahIkanMati = jumlahIkanMati;
    }

    public boolean isPembersihan() {
        return pembersihan;
    }

    public void setPembersihan(boolean pembersihan) {
        this.pembersihan = pembersihan;
    }
}
