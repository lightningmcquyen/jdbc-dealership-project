package com.pluralsight.dealership;

import dao.VehicleDAO;
import dao.VehicleDAOMysqlImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import java.util.Scanner;
import java.util.List;
import java.sql.*;

public class UserInterface {
    private final VehicleDAO vehicleDao;
    private final Scanner scanner;

    // Constructor to initialize DAO with the provided DataSource
    public UserInterface(BasicDataSource dataSource) {
        this.vehicleDao = new VehicleDAOMysqlImpl(dataSource);
        this.scanner = new Scanner(System.in);
    }

    public void display() {
        while (true) {
            System.out.println("""
                    🏁 Welcome to the Dealership 💨
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
                    (J) Quit
                    ================================
                    Enter your choice:
                    """);
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "A" -> searchByPrice();
                case "B" -> searchByMakeModel();
                case "C" -> searchByYear();
                case "D" -> searchByColor();
                case "E" -> searchByMileage();
                case "F" -> searchByType();
                case "G" -> displayAllVehicles();
                case "H" -> addVehicle();
                case "I" -> removeVehicle();
                case "J" -> quit();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void searchByPrice() {
        System.out.print("Enter minimum price: ");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum price: ");
        double maxPrice = Double.parseDouble(scanner.nextLine());
        List<Vehicle> vehicles = vehicleDao.findVehicleByPriceRange(minPrice, maxPrice);
        vehicles.forEach(System.out::println);
    }

    private void searchByMakeModel() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = vehicleDao.findVehicleByMakeModel(make, model);
        vehicles.forEach(System.out::println);
    }

    private void searchByYear() {
        System.out.print("Enter minimum year: ");
        int minYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maximum year: ");
        int maxYear = Integer.parseInt(scanner.nextLine());
        List<Vehicle> vehicles = vehicleDao.findVehicleByYear(minYear, maxYear);
        vehicles.forEach(System.out::println);
    }

    private void searchByColor() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = vehicleDao.findVehicleByColor(color);
        vehicles.forEach(System.out::println);
    }

    private void searchByMileage() {
        System.out.print("Enter minimum mileage: ");
        int minMileage = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter maximum mileage: ");
        int maxMileage = Integer.parseInt(scanner.nextLine());
        List<Vehicle> vehicles = vehicleDao.findVehicleByMileage(minMileage, maxMileage);
        vehicles.forEach(System.out::println);
    }

    private void searchByType() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();
        List<Vehicle> vehicles = vehicleDao.findVehicleByType(type);
        vehicles.forEach(System.out::println);
    }

    private void displayAllVehicles() {
        List<Vehicle> vehicles = vehicleDao.findAllVehicles();
        vehicles.forEach(System.out::println);
    }

    private void addVehicle() {
        System.out.print("Enter VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter odometer reading: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Is the vehicle sold? (true/false): ");
        boolean sold = Boolean.parseBoolean(scanner.nextLine());
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, sold);
        vehicleDao.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
    }

    private void removeVehicle() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());
        boolean removed = vehicleDao.removeVehicle(vin);
        if (removed) {
            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("No vehicle found with that VIN.");
        }
    }

    private void quit() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }
}