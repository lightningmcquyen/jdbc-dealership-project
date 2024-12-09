package com.pluralsight.dealership;

public class LeaseContract extends Contract {
    private final double originalPrice; // Price of the vehicle
    private final boolean financed; // Whether the lease is financed

    // Constructor
    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicle, double originalPrice, boolean financed) {
        super(dateOfContract, customerName, customerEmail, vehicle); // Call to Contract constructor
        this.originalPrice = originalPrice;
        this.financed = financed;
    }

    @Override
    public double getTotalPrice() {
        double expectedEndingValue = originalPrice * 0.50; // Expected ending value (50% of original price)
        double leaseFee = originalPrice * 0.07; // Lease fee (7% of original price)
        return originalPrice + leaseFee + expectedEndingValue; // Total price
    }

    public boolean isFinanced() {
        return financed;
    }

    public double getOriginalPrice() { // Getter for originalPrice
        return originalPrice;
    }

    public int getVin() { // Add this method to retrieve VIN
        return getVehicleSold().getVin(); // Access the VIN from the Vehicle object
    }

    @Override
    public double getMonthlyPayment() {
        if (!financed) return 0; // No payment if not financed
        double totalPrice = getTotalPrice();
        double interestRate = 0.04; // Fixed interest rate for leases
        int months = 36; // Lease duration
        return (totalPrice * interestRate / 12) / (1 - Math.pow(1 + interestRate / 12, -months));
    }
}