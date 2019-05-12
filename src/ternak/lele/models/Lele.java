package ternak.lele.models;

public class Lele extends Barang {

    private int idKolam;

    public Lele(int id, String namaBarang, int hargaBarang) {
        super(id, namaBarang, hargaBarang);
    }

    public int getIdKolam() {
        return idKolam;
    }

    public void setIdKolam(int idKolam) {
        this.idKolam = idKolam;
    }
}
