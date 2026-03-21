package org.example;

public class Main {
    public static void main(String[] args) {
        SmartHome home = new SmartHome();

        SmartLight light = new SmartLight("Лампа",RoomType.BEDROOM);
        SmartTV tv = new SmartTV("Домашний кинотеатр", RoomType.LIVING_ROOM);
        SmartThermostat thermostat = new SmartThermostat("Термометр",RoomType.KITCHEN);

        home.addDevices(light);
        home.addDevices(tv);
        home.addDevices(thermostat);

        home.turnAllOn();

        light.increaseValue();
        tv.decreaseValue();
        thermostat.increaseValue();

        home.showDevices();

        SmartHome.HomeStats.showStats();
        SmartHome.HomeStats.deviceTurnedOn();
        SmartHome.HomeStats.deviceTurnOff();
        SmartHome.HomeStats.deviceUsed();

        home.turnAllOff();
    }
}