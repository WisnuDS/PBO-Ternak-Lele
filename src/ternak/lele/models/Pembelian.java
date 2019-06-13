package ternak.lele.models;

public class Pembelian extends Transaksi{

    public static final String TABLE = "pembelian";

    public Pembelian(Barang barang, int jumlahBarang) {
        super(barang, jumlahBarang);
    }

    public static void createPembelianBibit(int jumlah, int hargaUnit){

    }
}
