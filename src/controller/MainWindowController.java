package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import utils.LoadTest;
import view.loaders.*;
import java.util.*;

public class MainWindowController implements Observer { //

    @FXML private TableView<Dam> damTable;
    @FXML private TableColumn<Dam, String> damName;
    @FXML private TableColumn<Dam, String> damCity;
    @FXML private TableColumn<Dam, Integer> damSisms;
    @FXML private TableColumn<Dam, String> damAlertState;
    @FXML private TableColumn<Dam, Integer> sismicSensorsCount;
    @FXML private TableColumn<Dam, Integer> sismicSensorsWithAlarmCount;
    @FXML private TableColumn<Dam, Integer> colapseAlarmCount;


    private static ObservableList<Dam> dams;
    private static Map<String, IntelligentDevice> intelligentDeviceMap = new HashMap<>();

    public void fillTable(){
        bindTableToEntity();
        dams = FXCollections.observableArrayList();
        dams.addAll(LoadTest.loadDam());
        LoadTest.bindDamToController(this, dams);
        intelligentDeviceMap = LoadTest.loadDevices();
        damTable.setItems(dams);
    }
    public void bindTableToEntity(){
        damName.setCellValueFactory(new PropertyValueFactory<>("name"));
        damCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        damAlertState.setCellValueFactory(new PropertyValueFactory<>("alertState"));
        damSisms.setCellValueFactory(new PropertyValueFactory<>("sismicCounter"));
        sismicSensorsCount.setCellValueFactory(new PropertyValueFactory<>("sismicSensorsCount"));
        sismicSensorsWithAlarmCount.setCellValueFactory(new PropertyValueFactory<>("sismicSensorsWithAlarmCount"));
        colapseAlarmCount.setCellValueFactory(new PropertyValueFactory<>("colapseAlarmCount"));
    }

    public void showDetails(){
        if(!damTable.getSelectionModel().isEmpty()) {
            DetailsDisplay detailsDisplay = new DetailsDisplay();
            detailsDisplay.showAndWait(damTable.getSelectionModel().getSelectedItem());
        }
    }

    public void showDamWindow(){
        DamWindow damWindow = new DamWindow();
        damWindow.showAndWait();
    }

    public void showSismicSensorWindow(){
        SismicSensorWindow sismicSensorWindow = new SismicSensorWindow();
        sismicSensorWindow.showAndWait();
    }
    public void showSismicSensorWithAlarmWindow(){
        SismicSensorWithAlarmWindow sismicSensorWithAlarmWindow = new SismicSensorWithAlarmWindow();
        sismicSensorWithAlarmWindow.showAndWait();
    }
    public void showColapseAlarmWindow(){
        ColapseAlarmWindow colapseAlarmWindow = new ColapseAlarmWindow();
        colapseAlarmWindow.showAndWait();
    }

    public static IntelligentDevice getIntelligentDevice(String id) {
        return intelligentDeviceMap.get(id);
    }

    public static Map<String, IntelligentDevice> getIntelligentDeviceMap() {
        return intelligentDeviceMap;
    }

    public static Dam getDam(String text) {
        for (Dam dam:
                dams) {
            if(dam.getName().equalsIgnoreCase(text)){
                return dam;
            }
        }
        return null;
    }

    public static List<Dam> getDamsList() {
        return dams;
    }

    public static void addDam(String name, String city){
        if(getDam(name) == null){
            Dam dam = new Dam(name, city);
            dam.addObserver(MainWindow.getController());
            dams.add(dam);
        }
    }

    public static void removeDam(String name, String city){
        if(getDam(name) != null){
            Dam dam = getDam(name);
            dams.remove(dam);
        }
    }

    public static void addSismicSensor(String id, Dam dam){
        IntelligentDevice d = new SismicSensor(id);
        d.addObserver(dam);
        intelligentDeviceMap.put(id, d);
    }

    public static void addSismicSensorWithAlarm(String id,Dam dam){
        IntelligentDevice d = new SismicSensorWithAlarm(id);
        d.addObserver(dam);
        intelligentDeviceMap.put(id, d);
    }

    public static void addColapseAlarm(String id, Dam dam){
        IntelligentDevice d = new ColapseAlarm(id);
        d.addObserver(dam);
        intelligentDeviceMap.put(id, d);
    }

    public void removeDevice(String id){
        intelligentDeviceMap.remove(id);
    }

    public void setLeituras(){
        LoadTest.setReadings();
    }

    @Override
    public void update(Observable o, Object arg) {
        damTable.refresh();
    }
}
