package ternak.lele;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ternak.lele.helpers.DBHelper;
import ternak.lele.helpers.GeneralHelper;
import ternak.lele.models.Pembelian;
import ternak.lele.models.Pemeliharaan;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));

//        Map<String, String> test = new HashMap<String, String>();
//        test.put("jumlah", "10");
//        test.put("harga_unit", "1000");
//        test.put("barang", "'Bibit  '");
//        DBHelper.update("pembelian", test, "id = 1");

//        System.out.println(((new Gson()).toJson(Pemeliharaan.getPemeliharaanByHari(1, 1))));

//        System.out.println(GeneralHelper.stringToMD5("asd").toLowerCase());

//        System.out.println(Pembelian.getAllPembelian().size());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
