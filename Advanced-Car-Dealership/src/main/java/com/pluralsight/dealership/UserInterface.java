package com.pluralsight.dealership;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private boolean isRunning; // Control variable for the menu loop
    private final Scanner scanley;

    public UserInterface() {
        this.isRunning = true; // Initialize running state
        this.scanley = new Scanner(System.in); // Create a scanner instance
        init(); // Initialize the dealership
    }

    // Display method
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found..."); // Message if no vehicles are found
        } else {
            for (Vehicle car : vehicles) {
                System.out.println(car); // Print each vehicle's details
            }
        }
    }

    // Method to initialize the dealership from file
    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        this.dealership = manager.getDealership(); // Load dealership data
    }

    // Method to quit the application
    public void quit() {
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership); // Save dealership data
        System.out.println("""
                 D & B Used Cars|111 Old Benbrook Rd|817-555-5555
                🚗 Thank you for stopping by. Please come again! 🚚
                """);
        isRunning = false; // Set running state to false
    }

    // Method to display the main menu
    public void displayMenu() {
        System.out.println("""
                🏁 Welcome to D & B Used Cars 💨
                ================================
                (A) Display Vehicles By Price
                (B) Display Vehicles By Make/Model
                (C) Display Vehicles By Year
                (D) Display Vehicles By Color
                (E) Display Vehicles By Mileage
                (F) Display Vehicles By Vehicle Type
                (G) Display All Vehicles
                (H) Add a Vehicle
                (I) Remove a Vehicle
                (J) Record a Sale
                (K) Record a Lease
                (X) Quit
                ================================
                Enter your choice:
                """);
    }

    // Main display loop
    public void display() {
        while (isRunning) {
            displayMenu(); // Show the menu and get choice
            String option = scanley.nextLine().toUpperCase();

            // Handle menu options
            switch (option) {
                case "A" -> processGetByPriceRequest();
                case "B" -> processGetByMakeModelRequest();
                case "C" -> processGetByYearRequest();
                case "D" -> processGetByColorRequest();
                case "E" -> processGetByMileageRequest();
                case "F" -> processGetByVehicleTypeRequest();
                case "G" -> processGetAllVehiclesRequest();
                case "H" -> processAddVehicleRequest();
                case "I" -> processRemoveVehicleRequest();
                case "J" -> processSaleVehicleRequest();
                case "K" -> processLeaseVehicleRequest();
                case "X" -> quit();
                default -> System.out.println("Invalid option. Please choose a valid option."); // Handle invalid input
            }
        }
    }

    // Method to process vehicle search by price
    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double minPrice = scanley.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanley.nextDouble();
        scanley.nextLine(); // Clear the scanner buffer

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles); // Display matching vehicles
    }

    // Method to process vehicle search by make and model
    public void processGetByMakeModelRequest() {
        System.out.print("Enter vehicle make: ");
        String make = scanley.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanley.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles); // Display matching vehicles
    }

    // Method to process vehicle search by year range
    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanley.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanley.nextInt();
        scanley.nextLine(); // Clear the scanner buffer

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles); // Display matching vehicles
    }

    // Method to process vehicle search by color
    public void processGetByColorRequest() {
        System.out.print("Enter vehicle color: ");
        String color = scanley.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles); // Display matching vehicles
    }

    // Method to process vehicle search by mileage range
    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double minMileage = scanley.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double maxMileage = scanley.nextDouble();
        scanley.nextLine(); // Clear the scanner buffer

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles); // Display matching vehicles
    }

    // Method to process vehicle search by type
    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanley.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles); // Display matching vehicles
    }

    // Method to display all vehicles in inventory
    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles()); // Display all vehicles
    }

    // Method to add a new vehicle
    public void processAddVehicleRequest() {
        System.out.println("Enter VIN:");
        int vin = Integer.parseInt(scanley.nextLine());
        System.out.println("Enter Year:");
        int year = Integer.parseInt(scanley.nextLine());
        System.out.println("Enter Make:");
        String make = scanley.nextLine();
        System.out.println("Enter Model:");
        String model = scanley.nextLine();
        System.out.println("Enter Vehicle Type:");
        String vehicleType = scanley.nextLine();
        System.out.println("Enter Color:");
        String color = scanley.nextLine();
        System.out.println("Enter Odometer:");
        int odometer = Integer.parseInt(scanley.nextLine());
        System.out.println("Enter Price:");
        double price = Double.parseDouble(scanley.nextLine());

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle); // Add vehicle to dealership
        System.out.println("Vehicle added successfully!");
    }

    // Method to remove a vehicle by VIN
    public void processRemoveVehicleRequest() {
        System.out.println("Enter the VIN of the vehicle to remove:");
        int vin = Integer.parseInt(scanley.nextLine());

        if (dealership.removeVehicle(vin)) {
            System.out.println("Vehicle removed successfully!"); // Success message
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found..."); // Error message
        }
    }

    // Method to record a vehicle sale
    public void processSaleVehicleRequest() {
        System.out.print("Enter date of contract (MM-DD-YYYY): ");
        String dateOfContract = scanley.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = scanley.nextLine();

        System.out.print("Enter customer email: ");
        String customerEmail = scanley.nextLine();

        System.out.print("Enter VIN of the vehicle sold: ");
        int vin = Integer.parseInt(scanley.nextLine());

        Vehicle vehicleSold = null;
        // Find the sold vehicle by VIN
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleSold = vehicle;
                break;
            }
        }
        if (vehicleSold == null) {
            System.out.println("Vehicle not found..."); // Error message if vehicle not found
            return;
        }

        System.out.print("Enter original price: ");
        double originalPrice = Double.parseDouble(scanley.nextLine());

        System.out.print("Do you want to finance? (yes/no): ");
        String financeInput = scanley.nextLine();
        boolean finance = financeInput.equalsIgnoreCase("yes");

        // Create a SalesContract
        SalesContract salesContract = new SalesContract(dateOfContract, customerName, customerEmail, vehicleSold, originalPrice, finance);
        System.out.println("Sale contract created successfully!");

        // Save the contract to the CSV file
        ContractFileManager contractFileManager = new ContractFileManager();
        contractFileManager.saveContract(salesContract);
    }



    // Method to record a vehicle lease
    public void processLeaseVehicleRequest() {
        System.out.print("Enter date of contract (MM-DD-YYYY): ");
        String dateOfContract = scanley.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = scanley.nextLine();

        System.out.print("Enter customer email: ");
        String customerEmail = scanley.nextLine();

        System.out.print("Enter VIN of the vehicle leased: ");
        int vin = Integer.parseInt(scanley.nextLine());

        Vehicle vehicleLeased = null;
        // Find the leased vehicle by VIN
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleLeased = vehicle;
                break;
            }
        }
        if (vehicleLeased == null) {
            System.out.println("Vehicle not found."); // Error message if vehicle not found
            return;
        }

        System.out.print("Enter original price: ");
        double originalPrice = Double.parseDouble(scanley.nextLine());

        System.out.print("Do you want to finance? (yes/no): ");
        String financeInput = scanley.nextLine();
        boolean finance = financeInput.equalsIgnoreCase("yes");

        // Create a LeaseContract
        LeaseContract leaseContract = new LeaseContract(dateOfContract, customerName, customerEmail, vehicleLeased, originalPrice, finance);
        System.out.println("Lease contract created successfully!");

        // Save the contract to the CSV file
        ContractFileManager contractFileManager = new ContractFileManager();
        contractFileManager.saveContract(leaseContract);
    }
}