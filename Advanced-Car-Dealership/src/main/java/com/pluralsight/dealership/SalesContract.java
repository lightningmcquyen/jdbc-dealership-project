package com.pluralsight.dealership;

public class SalesContract extends Contract {
    private final double originalPrice;
    private final boolean financed;

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicle, double originalPrice, boolean financed) {
        super(dateOfContract, customerName, customerEmail, vehicle);
        this.originalPrice = originalPrice;
        this.financed = financed;
    }

    @Override
    public double getTotalPrice() {
        double salesTax = originalPrice * 0.05;
        double recordingFee = 100.00;
        double processingFee = originalPrice < 10000 ? 295.00 : 495.00;
        return originalPrice + salesTax + recordingFee + processingFee;
    }

    public boolean isFinanced() {
        return financed;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public int getVin() {
        return getVehicleSold().getVin();
    }

    @Override
    public double getMonthlyPayment() {
        if (!financed) return 0;
        double totalPrice = getTotalPrice();
        double interestRate = originalPrice >= 10000 ? 0.0425 : 0.0525;
        int months = originalPrice >= 10000 ? 48 : 24;
        return (totalPrice * interestRate / 12) / (1 - Math.pow(1 + interestRate / 12, -months));
    }
}