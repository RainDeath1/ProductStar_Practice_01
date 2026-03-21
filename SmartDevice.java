package org.example;

public abstract class SmartDevice {
    protected String name;
    protected boolean isOn;
    RoomType roomtype;
    protected Gender gender;

    public SmartDevice(String name, RoomType roomtype, Gender gender){
        this.name = name;
        this.isOn = false;
        this.roomtype = roomtype;
        this.gender = gender;
    }
    public final void turnOn(){
        if(!isOn) {
            isOn = true;
            System.out.println(name + " " + getOnWord());
            SmartHome.HomeStats.deviceUsed();
        }
    }

    public final void turnOff(){
        isOn = false;
        System.out.println(name + " " + getOffWord());
    }

    public RoomType getRoomtype(){
        return  roomtype;
    }

    public String getName(){
        return name;
    }

    private String getOnWord(){
        return switch (gender){
            case Gender.MASCULINE -> "включен";
            case Gender.FEMININE -> "включена";
            case Gender.NEUTER -> "включено";
        };
    }

    private String getOffWord(){
        return switch (gender){
            case Gender.MASCULINE -> "выключен";
            case Gender.FEMININE -> "выключена";
            case Gender.NEUTER -> "выключено";
        };
    }


}
