package org.example;

public class Pastry extends MenuItem{
    private boolean isSweet;

    public Pastry(String name, int price, boolean isSweet){
        super(name, price);
        this.isSweet = isSweet;
    }
}
