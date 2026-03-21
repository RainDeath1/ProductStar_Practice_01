package org.example;

public class Tea extends MenuItem implements Preparable{
    private Size size;
    private String type;

    public Tea(String name,int price, String type, Size size){
        super(name, price);
        this.type = type;
        this.size = size;
    }
    @Override
    public void prepare() {
        System.out.println("Готовим " + type + ", чай " + getName() + "[ " + size + "]" + ". " + getPrice());
    }
}
