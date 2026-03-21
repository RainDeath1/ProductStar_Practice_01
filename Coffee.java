package org.example;

public class Coffee  extends MenuItem implements Preparable{
        private String strength;
        private Size size;

        public Coffee(String name,int price, String strength, Size size){
            super(name, price);
            this.strength = strength;
            this.size = size;
        }
        @Override
        public void prepare() {
            System.out.println("Готовим кофе" + getName() +  "[ " + size + "]" + ". " + getPrice());
        }
    }


