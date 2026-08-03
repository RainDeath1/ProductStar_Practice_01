package org.example.creditbot.calculator;

import  org.example.creditbot.model.CreditRequest;
import  org.example.creditbot.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class AnnuityCalculator implements PaymentCalculator{

    @Override
    public List<Payment> calculate(CreditRequest request){
        List<Payment> payments = new ArrayList<>();

        double loanAmount = request.getAmount();
        int months = request.getMonths();
        double monthlyRate = request.getAnnualRate()/ 100/ 12;

        double coefficient =
                (monthlyRate * Math.pow(1 + monthlyRate, months))/(Math.pow(1 + monthlyRate, months) - 1);

        double monthlyPayment = loanAmount * coefficient;

        double remainingDebt = loanAmount;

        for(int month = 1; month <= months; month++){
            double interest = remainingDebt * monthlyRate;
            double principal = monthlyPayment - interest;

            remainingDebt -= principal;

            if(remainingDebt <0){
                remainingDebt = 0;
            }

            payments.add(new Payment(
                    month,
                    round(monthlyPayment),
                    round(principal),
                    round(interest),
                    round(remainingDebt)
            ));
        }

        return payments;
    }
    private  double round(double value){
        return Math.round(value * 100.0)/ 100.0;
    }
}