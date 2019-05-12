package ternak.lele.models;

public class Pakan extends Barang {

    int jumlahPakan;

    public Pakan(int id, String namaBarang, int hargaBarang) {
        super(id, namaBarang, hargaBarang);
        this.jumlahPakan = 0;
    }

    public int getJumlahPakan() {
        return jumlahPakan;
    }

    public void setJumlahPakan(int jumlahPakan) {
        this.jumlahPakan = jumlahPakan;
    }
}
