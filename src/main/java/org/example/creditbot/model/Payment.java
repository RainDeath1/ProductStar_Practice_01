package org.example.creditbot.model;

public class Payment {

    private final int month;
    private final double totalPayment;
    private final double principal;
    private final double interest;
    private final double remainingDebt;

    public Payment(int month,
                   double totalPayment,
                   double principal,
                   double interest,
                   double remainingDebt) {

        this.month = month;
        this.totalPayment = totalPayment;
        this.principal = principal;
        this.interest = interest;
        this.remainingDebt = remainingDebt;
    }

    public int getMonth() {
        return month;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getInterest() {
        return interest;
    }

    public double getRemainingDebt() {
        return remainingDebt;
    }

    @Override
    public String toString() {
        return String.format(
                "Месяц: %d%nПлатеж: %.2f%nОсновной долг: %.2f%nПроценты: %.2f%nОстаток: %.2f",
                month,
                totalPayment,
                principal,
                interest,
                remainingDebt
        );
    }
}