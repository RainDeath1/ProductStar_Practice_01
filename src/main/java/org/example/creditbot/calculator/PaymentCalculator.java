package org.example.creditbot.calculator;

import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.Payment;

import java.util.List;

public interface PaymentCalculator {

    List<Payment> calculate(CreditRequest request);
}