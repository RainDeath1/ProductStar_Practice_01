package org.example.creditbot.model;

public enum PaymentType {

    ANNUITY("Аннуитетный"),
    DIFFERENTIAL("Дифференцированный");


    private final String title;


    PaymentType(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }
}