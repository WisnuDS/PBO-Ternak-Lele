package ternak.lele.models;

public class Obat extends Barang{

    private int jumlahObat;

    public Obat(int id, String namaBarang) {
        super(id, namaBarang);
    }

    public int getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(int jumlahObat) {
        this.jumlahObat = jumlahObat;
    }
}
