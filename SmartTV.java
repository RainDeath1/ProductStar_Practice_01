package org.example;

public class SmartTV extends SmartDevice implements Controllable{
    private int volume = 10;

    public SmartTV(String name, RoomType roomType){
        super(name,roomType, Gender.MASCULINE);
    }

    @Override
    public void increaseValue() {
        volume++;
        System.out.println("Громкость повышена до " + volume);
    }

    @Override
    public void decreaseValue() {
        volume--;
        System.out.println("Громкость уменьшена до " + volume);
    }
}
