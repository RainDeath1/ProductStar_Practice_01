package org.example;

public class Circle {
    private  double radius;

    public Circle(double radius){
        if(radius <= 0){
            throw new IllegalArgumentException("Радиус должен быть больше нуля. " +
                    "Передано: " + radius);
        }
        this.radius = radius;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius) {

        if(radius <= 0){
            throw new IllegalArgumentException("Радиус должен быть " +
                    "больше нуля. " +
                    "Передано: " + radius);
        }
        this.radius = radius;
    }

    public double getArea(){
        return Math.PI * Math.pow(radius, 2);
    }

}
