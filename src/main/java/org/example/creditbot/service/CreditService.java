package org.example.creditbot.service;

import org.example.creditbot.calculator.PaymentCalculator;
import org.example.creditbot.factory.CalculatorFactory;
import org.example.creditbot.model.CreditRequest;
import org.example.creditbot.model.Payment;
import org.example.creditbot.repository.RequestRepository;

import java.util.List;

public class CreditService {

    private final CalculatorFactory calculatorFactory;
    private final RequestRepository repository;

    public CreditService(CalculatorFactory calculatorFactory,
                         RequestRepository repository) {

        this.calculatorFactory = calculatorFactory;
        this.repository = repository;
    }

    public List<Payment> calculate(CreditRequest request) {

        repository.save(request);

        PaymentCalculator calculator =
                calculatorFactory.getCalculator(request.getPaymentType());

        return calculator.calculate(request);
    }
}