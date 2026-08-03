package org.example.creditbot.model;

import java.time.LocalDateTime;

public class CreditRequest {

    private final long chatId;
    private final double amount;
    private final int months;
    private final double annualRate;
    private final PaymentType paymentType;
    private final LocalDateTime createdAt;

    public CreditRequest(long chatId,
                         double amount,
                         int months,
                         double annualRate,
                         PaymentType paymentType) {

        this.chatId = chatId;
        this.amount = amount;
        this.months = months;
        this.annualRate = annualRate;
        this.paymentType = paymentType;
        this.createdAt = LocalDateTime.now();
    }

    public long getChatId() {
        return chatId;
    }

    public double getAmount() {
        return amount;
    }

    public int getMonths() {
        return months;
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "CreditRequest{" +
                "chatId=" + chatId +
                ", amount=" + amount +
                ", months=" + months +
                ", annualRate=" + annualRate +
                ", paymentType=" + paymentType +
                ", createdAt=" + createdAt +
                '}';
    }
}