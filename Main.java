package org.example;

public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle(5);
        System.out.println("Радиус: " + circle.getRadius());
        System.out.println("Площадь: " + circle.getArea());

        circle.setRadius(10);
        System.out.println("Радиус: " + circle.getRadius());
        System.out.println("Площадь: " + circle.getArea());
        
        try {
            circle.setRadius(-1);
        }catch (IllegalArgumentException e){
            System.out.println("Ошибка: "+ e.getMessage());
        }
        try {
            Circle leperCircle = new Circle(-2);
        } catch (IllegalArgumentException e){
            System.out.println("Ошибка: "+ e.getMessage());
        }
    }
}