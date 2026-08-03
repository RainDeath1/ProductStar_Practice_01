package org.example.creditbot.factory;

import org.example.creditbot.calculator.AnnuityCalculator;
import org.example.creditbot.calculator.DifferentialCalculator;
import org.example.creditbot.calculator.PaymentCalculator;
import org.example.creditbot.model.PaymentType;

public class CalculatorFactory {

    private final PaymentCalculator annuityCalculator = new AnnuityCalculator();
    private final PaymentCalculator differentialCalculator = new DifferentialCalculator();

    public PaymentCalculator getCalculator(PaymentType paymentType) {
        return switch (paymentType) {
            case ANNUITY -> annuityCalculator;
            case DIFFERENTIAL -> differentialCalculator;
        };
    }
}