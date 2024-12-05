package com.pluralsight.dealership;

public class SalesContract extends Contract {
    final private double originalPrice;
    final private boolean financed;

    // Constructor
    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicle, double originalPrice, boolean financed) {
        super(dateOfContract, customerName, customerEmail, vehicle); // Call to Contract constructor
        this.originalPrice = originalPrice;
        this.financed = financed;
    }

    public boolean isFinanced() {
        return financed;
    }


    @Override
    public double getTotalPrice() {
        double salesTax = originalPrice * 0.05; // Sales tax (5%)
        double recordingFee = 100.00; // Recording fee
        double processingFee = originalPrice < 10000 ? 295.00 : 495.00; // Processing fee
        return originalPrice + salesTax + recordingFee + processingFee; // Total price
    }

    @Override
    public double getMonthlyPayment() {
        if (!financed) return 0; // No payment if not financed
        double totalPrice = getTotalPrice();
        double interestRate = originalPrice >= 10000 ? 0.0425 : 0.0525; // Interest rate
        int months = originalPrice >= 10000 ? 48 : 24; // 4.25% for 48 months if price is $10K or more
        return (totalPrice * interestRate / 12) / (1 - Math.pow(1 + interestRate / 12, -months));
    }
}