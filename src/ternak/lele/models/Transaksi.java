package ternak.lele.models;

public class Transaksi {
    protected Barang barang;
    protected int jumlahBarang;

    public Transaksi(Barang barang, int jumlahBarang) {
        this.barang = barang;
        this.jumlahBarang = jumlahBarang;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }
}
