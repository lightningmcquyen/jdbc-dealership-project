package com.pluralsight.dealership;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String CONTRACTS_FILE_PATH = "src/main/resources/WorkshopFiles/contracts.csv"; // Change this to your desired path

    // Method to append a contract to the contracts file
    public void saveContract(Contract contract) {
        String contractData;

        if (contract instanceof SalesContract) {
            SalesContract salesContract = (SalesContract) contract;
            Vehicle vehicle = salesContract.getVehicleSold();

            double totalPrice = salesContract.getTotalPrice();
            double salesTax = totalPrice * 0.05; // Assuming 5% sales tax
            double recordingFee = 100.00;
            double processingFee = vehicle.getPrice() < 10000 ? 295.00 : 495.00; // processing fee logic

            contractData = String.format("SALE|Date of Contract: %s|Name: %s|Email: %s|VIN number: %d|Year: %d|Make: %s|Model: %s|Vehicle Type: %s|Color: %s|Odometer: %d|Subtotal: %.2f|Sales Tax: %.2f|Recording Fee: %.2f|Processing Fee: %.2f|Total: %.2f|Financed: %s|Monthly Payment: %.2f",
                    salesContract.getDateOfContract(),
                    salesContract.getCustomerName(),
                    salesContract.getCustomerEmail(),
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    totalPrice,
                    salesTax,
                    recordingFee,
                    processingFee,
                    totalPrice + salesTax + recordingFee + processingFee, // Final total
                    salesContract.isFinanced() ? "YES" : "NO", // Financing status
                    salesContract.getMonthlyPayment());

        } else if (contract instanceof LeaseContract) {
            LeaseContract leaseContract = (LeaseContract) contract;
            Vehicle vehicle = leaseContract.getVehicleSold();

            double totalPrice = leaseContract.getTotalPrice();
            double expectedEndingValue = totalPrice * 0.50; // expected ending value logic

            contractData = String.format("LEASE|Date of Contract: %s|Name: %s|Email: %s|VIN number: %d|Year: %d|Make: %s|Model: %s|Vehicle Type: %s|Color: %s|Odometer: %d|Subtotal: %.2f|Expected Ending Value: %.2f|Total: %.2f|Monthly Payment: %.2f|Financed: %s|Additional Value: %.2f",
                    leaseContract.getDateOfContract(),
                    leaseContract.getCustomerName(),
                    leaseContract.getCustomerEmail(),
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    totalPrice,
                    expectedEndingValue,
                    leaseContract.getTotalPrice(), // Total price for lease
                    leaseContract.getMonthlyPayment(), // Monthly payment
                    leaseContract.isFinanced() ? "YES" : "NO", // Financing status
                    0.00 // Placeholder for any additional value
            );
        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACTS_FILE_PATH, true))) {
            writer.write(contractData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving contract data: " + e.getMessage());
        }
    }
}