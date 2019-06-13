package ternak.lele.controllers;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PemeliharaanController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        YearMonth yearMonthObject = YearMonth.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH) + 1);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        ObservableList<Integer> data = FXCollections.observableArrayList();
        for(int i=1; i<=daysInMonth; i++){
            data.add(i);
        }
        hariBox1.setItems(data);
        hariBox1.setValue(1);
        hariBox2.setItems(data);
        hariBox2.setValue(1);
        hariBox3.setItems(data);
        hariBox3.setValue(1);
    }

    @FXML
    private JFXComboBox<Integer> hariBox1;

    @FXML
    private JFXComboBox<Integer> hariBox2;

    @FXML
    private JFXComboBox<Integer> hariBox3;
}
