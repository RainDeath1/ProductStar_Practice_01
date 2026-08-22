package org.example;

public class DeliveryCalculator {
    private static final double MIN_DELIVERY_COST = 400.0;

    public static double calculateDeliveryCost(
        double distance,
        String size,
        boolean fragile,
        String workload
    ){
        if(distance<0){
            throw new IllegalArgumentException("Расстояние не может быть отрицательны");
        }

        if (fragile&& distance > 30) {
            throw new IllegalArgumentException(
                    "Хрупкие грузы нельзя перевозить на расстояние более 30 км"
            );
        }

        double cost = 0;

        if(distance > 30){
            cost += 300;
        }else if(distance > 10){
            cost += 200;
        }else if (distance > 2){
            cost += 100;
        } else {
            cost += 50;
        }

        switch (size){
            case "large":
                cost += 200;
                break;
            case "small":
                cost += 100;
                break;
            default:
                throw new IllegalArgumentException(
                        "Размер груза должен быть 'small' или 'large'"
                );
        }

        if(fragile){
            cost += 300;
        }

        double coefficient;

        switch (workload){
            case "very_high":
                coefficient = 1.6;
                break;
            case "high":
                coefficient = 1.4;
                break;
            case  "elevated":
                coefficient = 1.2;
                break;
            case "normal":
                coefficient = 1.0;
                break;
            default:
                throw new IllegalArgumentException(
                        "Неизвестный уровень загруженности: " + workload
                );
        }

        cost *= coefficient;
        return Math.max(cost, MIN_DELIVERY_COST );
    }
}
