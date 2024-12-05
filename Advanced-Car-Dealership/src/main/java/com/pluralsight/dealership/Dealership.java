package com.pluralsight.dealership;

import java.util.ArrayList;

public class Dealership {
    // Instance variables
    private final ArrayList<Vehicle> inventory; //holds all vehicles
    private final DealershipFileManager fileManager; // Add a file manager reference
    private String name;
    private String address;
    private String phone;

    //Constructor to create dealership objects and initialize inventory
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.inventory = new ArrayList<>(); //initialize the ArrayList
        this.fileManager = new DealershipFileManager(); // Initialize the file manager
    }

    // Getters and Setters
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {

        this.phone = phone;
    }


    // Methods for filtering
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> result = new ArrayList<>(); // List to hold filter matching vehicles
        // Loop through all vehicles in the inventory
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                result.add(vehicle); // Add matching vehicle to the result list
            }
        }
        return result; // Return the list of matching vehicles
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model){

        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            // Check if the make and model match
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max){
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color){
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(double min, double max){
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType){
        ArrayList<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }

    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
        fileManager.saveDealership(this); // Save after adding
    }

    public boolean removeVehicle(int vin) {
        // Iterate through the inventory to find the vehicle with the given VIN
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle vehicle = inventory.get(i); // Get the vehicle at index i
            // Check if the VIN matches
            if (vehicle.getVin() == vin) {
                inventory.remove(i); // Remove the vehicle from the inventory
                // Save the updated inventory to the CSV file
                DealershipFileManager fileManager = new DealershipFileManager();
                fileManager.saveDealership(this); // Save changes after removal
                return true; // Vehicle found and removed
            }
        }
        return false; // Vehicle not found
    }


    @Override
    public String toString() {
        return "Dealership{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}