package model;

import controller.MainWindowController;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Dam extends Observable implements Observer {
    private String name, city;
    private int sismicCounter = 0;
    private final String inAlertText = "In Alert";
    private final String offAlertText = "Stable";
    private String alertState = offAlertText;
    private final int limit1 = 2;
    private final int limit2 = 3;
    private final int limit3 = 6;

    private List<String> sismicSensorsList = new ArrayList<>();
    private List<String> sismicSensorsWithAlarmList = new ArrayList<>();
    private List<String> colapseAlarmsList = new ArrayList<>();

    public Dam(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        setChanged();
        notifyObservers();
    }

    public int getSismicCounter() {
        return sismicCounter;
    }

    public void setSismicCounter(int sismicCounter) {
        this.sismicCounter = sismicCounter;
        setChanged();
        notifyObservers();
    }

    public String getAlertState() {
        return alertState;
    }

    public List<String> getSismicSensorsList() {
        return (List<String>) sismicSensorsList;
    }

    public int getSismicSensorsCount(){
        return sismicSensorsList.size();
    }

    public List<String> getSismicSensorsWithAlarmList() {
        return (List<String>) sismicSensorsWithAlarmList;
    }

    public int getSismicSensorsWithAlarmCount(){
        return sismicSensorsWithAlarmList.size();
    }

    public List<String> getColapseAlarmsList() {
        return (List<String>) colapseAlarmsList;
    }

    public int getColapseAlarmCount(){
        return colapseAlarmsList.size();
    }

    public void addSism(){
        sismicCounter++;
    }

    public void removeSism(){
        sismicCounter--;
    }

    @Override
    public void update(Observable o, Object arg) {
        sismicCounter = 0;
        for (String d:
             MainWindowController.getIntelligentDeviceMap().keySet()) {
            if(sismicSensorsList.contains(d) || sismicSensorsWithAlarmList.contains(d)){
                if(o instanceof SismicSensor) {
                    if (((SismicSensor) o).isShake()) {
                        addSism();
                    }
                } else if (o instanceof SismicSensorWithAlarm){
                    if(((SismicSensorWithAlarm) o).getShakeRead() >= ((SismicSensorWithAlarm) o).getSafetyLimit()){
                        addSism();
                    }
                }
            }
        }
        checkState();
    }

    private void checkState() {
        if(sismicCounter >= limit3){
            alertState = inAlertText;
            MainWindowController.getIntelligentDeviceMap().values().stream()
                    .filter(d -> (sismicSensorsList.contains(d)) || (sismicSensorsWithAlarmList.contains(d)) || (colapseAlarmsList.contains(d)))
                    .forEach(d-> d.alert(true));
        } else if (sismicCounter >= limit2){
            alertState = inAlertText;
            for (IntelligentDevice d:
                    MainWindowController.getIntelligentDeviceMap().values()) {
                if((sismicSensorsList.contains(d)) || (sismicSensorsWithAlarmList.contains(d))){
                    d.alert(true);
                } else if (colapseAlarmsList.contains(d)){
                    d.alert(false);
                }
            }
        } else if (sismicCounter >= limit1){
            alertState = inAlertText;
            for (IntelligentDevice d:
                    MainWindowController.getIntelligentDeviceMap().values()) {
                if(sismicSensorsList.contains(d)){
                    d.alert(true);
                } else if ((colapseAlarmsList.contains(d)) || (sismicSensorsWithAlarmList.contains(d))){
                    d.alert(false);
                }
            }
        } else {
            alertState = offAlertText;
            for (IntelligentDevice d:
                    MainWindowController.getIntelligentDeviceMap().values()) {
                d.alert(false);
            }
        }
        setChanged();
        notifyObservers();
    }

    public void addSismicSensor(String id) {
        sismicSensorsList.add(id);
        MainWindowController.addSismicSensor(id, this);
        setChanged();
        notifyObservers();
    }

    public void addSismicSensorWithAlarm(String id) {
        sismicSensorsWithAlarmList.add(id);
        MainWindowController.addSismicSensorWithAlarm(id, this);
        setChanged();
        notifyObservers();
    }

    public void addColapseAlarm(String id) {
        colapseAlarmsList.add(id);
        MainWindowController.addColapseAlarm(id, this);
        setChanged();
        notifyObservers();
    }

    public void removeDevice(String id) {
        if(MainWindowController.getIntelligentDevice(id) != null){
            if(sismicSensorsList.contains(id)){
                MainWindowController.getIntelligentDevice(id).deleteObserver(this);
                sismicSensorsList.remove(id);
                setChanged();
                notifyObservers();
            } else if (sismicSensorsWithAlarmList.contains(id)){
                MainWindowController.getIntelligentDevice(id).deleteObserver(this);
                sismicSensorsWithAlarmList.remove(id);
                setChanged();
                notifyObservers();
            } else if (colapseAlarmsList.contains(id)){
                MainWindowController.getIntelligentDevice(id).deleteObserver(this);
                colapseAlarmsList.remove(id);
                setChanged();
                notifyObservers();
            }
        }
    }
}
