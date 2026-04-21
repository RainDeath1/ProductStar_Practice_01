package org.example;

public class Workspace implements Comparable<Workspace> {
    private int number;
    private String type;
    private boolean isAvailable;

    public Workspace(int number, String type){
        this.number = number;
        this.type = type;
        this.isAvailable = true;
    }

    public int getNumber(){
        return number;
    }

    public String getType(){
        return type;
    }

    public boolean isAvailable(){
        return isAvailable;
    }

    public void markAsAvailable(){
        isAvailable = true;
    }

    public void markAsBooked(){
        isAvailable = false;
    }

    @Override
    public int compareTo(Workspace other) {
        return Integer.compare(this.number, other.number);
    }

    @Override
    public String toString(){
        return "№" + number + ", тип " + type;
    }
}
