package org.example;

public class Main {
    public static void main(String[] args) {

        Circle circle = new Circle(5);
        System.out.println("Радиус: " + circle.getRadius());
        System.out.println("Площадь: " + circle.getArea());

        circle.setRadius(10);
        System.out.println("Радиус: " + circle.getRadius());
        System.out.println("Площадь: " + circle.getArea());
    }
}