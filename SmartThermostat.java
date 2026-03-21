package org.example;

public class SmartThermostat extends SmartDevice implements Controllable {
    private int temperature = 22;

    public SmartThermostat(String name, RoomType roomType){
        super(name, roomType, Gender.MASCULINE);
    }
    @Override
    public void increaseValue() {
        temperature++;
        System.out.println("Температура увеличена до " + temperature);
    }

    @Override
    public void decreaseValue() {
        temperature--;
        System.out.println("Температура понижена до "+ temperature);
    }
}
