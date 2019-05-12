package ternak.lele.models;

public class Obat extends Barang {

    private int jumlahObat;

    public Obat(int id, String namaBarang, int hargaBarang) {
        super(id, namaBarang, hargaBarang);
    }

    public int getJumlahObat() {
        return jumlahObat;
    }

    public void setJumlahObat(int jumlahObat) {
        this.jumlahObat = jumlahObat;
    }
}
