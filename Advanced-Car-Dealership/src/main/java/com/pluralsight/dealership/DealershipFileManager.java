package com.pluralsight.dealership;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
    private static final String FILE_PATH = "src/main/resources/WorkshopFiles/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = new Dealership("Name", "Address", "Phone Number");
        List<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader buffy = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            // Reading dealership information
            if ((line = buffy.readLine()) != null) {
                String[] dealershipInfo = line.split("\\|"); // Split by '|'
                if (dealershipInfo.length == 3) {
                    String name = dealershipInfo[0];
                    String address = dealershipInfo[1];
                    String phone = dealershipInfo[2];
                    dealership.setName(name);
                    dealership.setAddress(address);
                    dealership.setPhone(phone);
                }
            }

            // Reading vehicle information
            while ((line = buffy.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split by '|'
                if (parts.length == 8) { // Ensure there are 8 fields
                    int vin = Integer.parseInt(parts[0]);
                    int year = Integer.parseInt(parts[1]);
                    String make = parts[2];
                    String model = parts[3];
                    String vehicleType = parts[4];
                    String color = parts[5];
                    int odometer = Integer.parseInt(parts[6]);
                    double price = Double.parseDouble(parts[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                    vehicles.add(vehicle);
                }
            }

            // Add vehicles to the dealership
            for (Vehicle vehicle : vehicles) {
                dealership.addVehicle(vehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
        return dealership; // Return the populated dealership
    }



    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write dealership information
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            // Write vehicle information
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving dealership data: " + e.getMessage());
        }
    }
}