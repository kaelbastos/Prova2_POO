package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;
import java.util.ArrayList;
import java.util.List;

public class SismicSensorWindowController {

    @FXML private TextField idInput;
    @FXML private ComboBox<String> damsChoice;
    @FXML private Button cancelButton;

    List<String> damsNames = new ArrayList<>();

    public void init(){
        MainWindowController.getDamsList().forEach(d -> damsNames.add(d.getName()));
        System.out.println(damsNames);
        damsChoice.getItems().addAll(damsNames);
    }

    public void addSismicSensor(){
        if(!idInput.getText().isEmpty() && !damsChoice.getSelectionModel().isEmpty()){
            IntelligentDevice device = new SismicSensor(idInput.getText());
            Dam dam = MainWindowController.getDam(damsChoice.getValue());
            String id = idInput.getText();
            dam.addSismicSensor(id);
            clear();
        }
    }

    public void removeDevice(){
        if(!idInput.getText().isEmpty() && !damsChoice.getSelectionModel().isEmpty()){
            Dam dam = MainWindowController.getDam(damsChoice.getValue());
            dam.removeDevice(idInput.getText());
            clear();
        }
    }

    public void clear(){
        idInput.setText("");
    }

    public void close(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
