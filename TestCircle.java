package org.example;

public class TestCircle {
    public static void main(String[] args) {
        testConstructor();
        testSetRadius();
        testGetArea();
    }

    public static void testConstructor(){
        System.out.println("Тест конструктора");

        Circle circle = new Circle(5);
        if(circle.getRadius() == 5){
            System.out.println("Корректный радиус");
        }else {
            System.out.println("Радиус не совпадаем");
        }

        try {
            new Circle(-1);
            System.out.println("Исключение не выброшено");
        } catch (IllegalArgumentException e){
            System.out.println("Исключение выброшено");
        }
    }

    public static void testSetRadius(){
        System.out.println("Тест назначения радиуса");
        Circle circle = new Circle(4);
        circle.setRadius(10);
        if(circle.getRadius() == 10){
            System.out.println("Радиус изменился");
        }else {
            System.out.println("Радиус не изменился");
        }

        try {
            circle.setRadius(-7);
            System.out.println("Исключение не выброшены");
        }catch (IllegalArgumentException e){
            System.out.println("Исключение отработано");
        }
    }

    public static void testGetArea(){
        System.out.println("Тест расчета пллощади");

        Circle circle = new Circle(2);
        double expected = Math.PI * 4;
        double actual = circle.getArea();

        if(Math.abs(expected-actual)<0.0001){
            System.out.println("Площадь верная");
        }else {
            System.out.println("Площадь неправильная");
        }
    }
}
