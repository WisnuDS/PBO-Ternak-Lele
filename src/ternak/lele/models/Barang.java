package ternak.lele.models;

public class Barang {
    private int id;
    private String namaBarang;
    private int hargaBarang;

    public Barang(int id, String namaBarang) {
        this.id = id;
        this.namaBarang = namaBarang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }
}
