package org.example;

import java.util.ArrayList;
import java.util.List;

public class SmartHome {
    private List<SmartDevice> devices= new ArrayList<>();

    public void addDevices(SmartDevice device){
        devices.add(device);
    }

    public void turnAllOn(){
        for(SmartDevice d : devices){
            d.turnOn();
        }
    }

    public void turnAllOff(){
        for (SmartDevice d : devices){
            d.turnOff();
        }
    }

    public void showDevices(){
        for (SmartDevice d : devices){
            System.out.println(d.getName() + " В комнате " + d.getRoomtype());
        }
    }
    public static class HomeStats{
        private static int deviseUsed = 0;
        private static int devicesOn = 0;
        public static void deviceUsed(){
            deviseUsed++;
        }

        public static void deviceTurnedOn(){
            devicesOn++;
        }

        public static void deviceTurnOff(){
            if(devicesOn>0) devicesOn--;
        }
        public static void showStats(){
            System.out.println("Использовано устройств: " + deviseUsed);
        }
    }
}
