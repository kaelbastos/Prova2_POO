package utils;

import controller.MainWindowController;
import model.ColapseAlarm;
import model.Dam;
import model.IntelligentDevice;
import model.SismicSensor;
import model.SismicSensorWithAlarm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LoadTest {
    public static List<Dam> loadDam(){
        List<Dam> list = new ArrayList<>();

        Dam dam1 = new Dam("xingo", "sao francisco");
        Dam dam2 = new Dam("itapebi", "jequitinhonha");
        Dam dam3 = new Dam("itaipu", "foz do Iguaçu");
        Dam dam4 = new Dam("brumado" , "brumado");

        list.add(dam1);
        list.add(dam2);
        list.add(dam3);
        list.add(dam4);

        return list;
    }

    public static Map<String, IntelligentDevice> loadDevices(){
        List<Dam> list = MainWindowController.getDamsList();
        Map<String, IntelligentDevice> intelligentDeviceMap = MainWindowController.getIntelligentDeviceMap();

        SismicSensor d1 = new SismicSensor("00001-1");
        SismicSensor d2 = new SismicSensor("00002-1");
        SismicSensor d3 = new SismicSensor("00003-1");
        SismicSensor d4 = new SismicSensor("00004-1");

        SismicSensor d5 = new SismicSensor("00005-1");
        SismicSensor d6 = new SismicSensor("00006-1");
        SismicSensor d7 = new SismicSensor("00007-1");
        SismicSensor d8 = new SismicSensor("00008-1");

        SismicSensorWithAlarm d9 = new SismicSensorWithAlarm("00009-2");
        SismicSensorWithAlarm d10 = new SismicSensorWithAlarm("00010-2");
        SismicSensorWithAlarm d11 = new SismicSensorWithAlarm("00011-2");
        SismicSensorWithAlarm d12 = new SismicSensorWithAlarm("00012-2");

        ColapseAlarm d13 = new ColapseAlarm("00013-3");
        ColapseAlarm d14 = new ColapseAlarm("00014-3");
        ColapseAlarm d15 = new ColapseAlarm("00015-3");
        ColapseAlarm d16 = new ColapseAlarm("00016-3");

        intelligentDeviceMap.put(d1.getId(), d1);
        intelligentDeviceMap.put(d2.getId(), d2);
        intelligentDeviceMap.put(d3.getId(), d3);
        intelligentDeviceMap.put(d4.getId(), d4);
        intelligentDeviceMap.put(d5.getId(), d5);
        intelligentDeviceMap.put(d6.getId(), d6);
        intelligentDeviceMap.put(d7.getId(), d7);
        intelligentDeviceMap.put(d8.getId(), d8);
        intelligentDeviceMap.put(d9.getId(), d9);
        intelligentDeviceMap.put(d10.getId(), d10);
        intelligentDeviceMap.put(d11.getId(), d11);
        intelligentDeviceMap.put(d12.getId(), d12);
        intelligentDeviceMap.put(d13.getId(), d13);
        intelligentDeviceMap.put(d14.getId(), d14);
        intelligentDeviceMap.put(d15.getId(), d15);
        intelligentDeviceMap.put(d16.getId(), d16);

        Dam dam = list.get(0);
        dam.addSismicSensor(d1.getId());
        dam.addSismicSensor(d2.getId());
        dam.addSismicSensor(d3.getId());
        dam.addSismicSensor(d4.getId());
        dam.addSismicSensor(d5.getId());
        dam.addSismicSensor(d6.getId());
        dam.addSismicSensor(d7.getId());
        dam.addSismicSensor(d8.getId());
        dam.addSismicSensorWithAlarm(d9.getId());
        dam.addSismicSensorWithAlarm(d10.getId());
        dam.addSismicSensorWithAlarm(d11.getId());
        dam.addSismicSensorWithAlarm(d12.getId());
        dam.addColapseAlarm(d13.getId());
        dam.addColapseAlarm(d14.getId());

        Dam dam1 = list.get(1);
        dam1.addSismicSensor(d1.getId());
        dam1.addSismicSensor(d2.getId());
        dam1.addSismicSensor(d5.getId());
        dam1.addSismicSensor(d6.getId());
        dam1.addSismicSensorWithAlarm(d11.getId());
        dam1.addSismicSensorWithAlarm(d12.getId());
        dam1.addColapseAlarm(d15.getId());
        dam1.addColapseAlarm(d16.getId());

        Dam dam2 = list.get(2);
        dam2.addSismicSensor(d3.getId());
        dam2.addSismicSensor(d4.getId());
        dam2.addSismicSensor(d5.getId());
        dam2.addSismicSensor(d6.getId());
        dam2.addSismicSensorWithAlarm(d11.getId());
        dam2.addSismicSensorWithAlarm(d12.getId());
        dam2.addColapseAlarm(d13.getId());
        dam2.addColapseAlarm(d16.getId());

        Dam dam3 = list.get(3);
        dam3.addSismicSensor(d5.getId());
        dam3.addSismicSensor(d6.getId());
        dam3.addSismicSensor(d7.getId());
        dam3.addSismicSensor(d8.getId());
        dam3.addSismicSensorWithAlarm(d11.getId());
        dam3.addSismicSensorWithAlarm(d12.getId());
        dam3.addColapseAlarm(d15.getId());
        dam3.addColapseAlarm(d16.getId());

        setReadings();
        return intelligentDeviceMap;
    }

    public static void setReadings(){
        Random random = new Random();
        Map<String, IntelligentDevice> map = MainWindowController.getIntelligentDeviceMap();
        for (IntelligentDevice device:
             map.values()) {
            if(device instanceof SismicSensor){
                ((SismicSensor) device).setShake(random.nextBoolean());
            } else if (device instanceof SismicSensorWithAlarm){
                ((SismicSensorWithAlarm) device).setShakeRead(random.nextDouble());
            }
        }
    }

    public static void bindDamToController(MainWindowController ctrl, List<Dam> dams){
        for (Dam dam:
             dams) {
            dam.addObserver(ctrl);
        }
    }
}
