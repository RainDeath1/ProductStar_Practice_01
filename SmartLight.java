package org.example;

public class SmartLight  extends SmartDevice implements Controllable{
    private int brightness = 50;

    public SmartLight(String name, RoomType roomType){
        super(name,roomType, Gender.FEMININE);
    }
    @Override
    public void increaseValue() {
        brightness += 10;
        System.out.println("Яркость увеличена до " + brightness);

    }

    @Override
    public void decreaseValue() {
        brightness -= 10;
        System.out.println("Яркость уменьшена до " + brightness);
    }
}
