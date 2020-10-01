package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.util.List;

public class DetailWindowController {

    @FXML private TableView<IntelligentDevice> deviceTable;
    @FXML private TableColumn<IntelligentDevice, String> deviceId;
    @FXML private TableColumn<IntelligentDevice, String> deviceState;;
    @FXML private TextField damName;
    @FXML private TextField damCity;
    @FXML private TextField damAlertState;
    @FXML private TextField damSisms;
    @FXML private Button closeButton;

    private Dam dam;
    private ObservableList<IntelligentDevice> observableDevices;

    public void init(Dam selectedItem) {
        bindToTable();
        dam = selectedItem;
        damName.setText(dam.getName());
        damCity.setText(dam.getCity());
        damAlertState.setText(dam.getAlertState());
        damSisms.setText(Integer.toString(dam.getSismicCounter()));

        observableDevices = FXCollections.observableArrayList();
        List<String> ssList = dam.getSismicSensorsList();
        List<String> ssaList = dam.getSismicSensorsWithAlarmList();
        List<String> caList = dam.getColapseAlarmsList();
        for (String d:
             MainWindowController.getIntelligentDeviceMap().keySet()) {
            if(ssList.contains(d) || ssaList.contains(d) || caList.contains(d)){
                observableDevices.add(MainWindowController.getIntelligentDevice(d));
            }
        }
        deviceTable.setItems(observableDevices);
    }

    public void bindToTable(){
        deviceId.setCellValueFactory(new PropertyValueFactory<>("id"));
        deviceState.setCellValueFactory(new PropertyValueFactory<>("state"));
    }

    public void close(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
