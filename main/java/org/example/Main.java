package org.example;

public class Main {

    public static void main(String[] args) {

        double cost = DeliveryCalculator.calculateDeliveryCost(
                15,
                "large",
                true,
                "high"
        );

        System.out.println("Стоимость доставки: " + cost);
    }
}