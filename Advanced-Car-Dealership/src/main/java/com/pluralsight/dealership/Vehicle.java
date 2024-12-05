package com.pluralsight.dealership;

// Instance variables
public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    // Constructor to create a Vehicle object with their given attributes
    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    // Getters and Setters
    public int getVin() {

        return vin;
    }

    public void setVin(int vin) {

        this.vin = vin;
    }

    public int getYear() {

        return year;
    }

    public void setYear(int year) {

        this.year = year;
    }

    public String getMake() {

        return make;
    }

    public void setMake(String make) {

        this.make = make;
    }

    public String getModel() {

        return model;
    }

    public void setModel(String model) {

        this.model = model;
    }

    public String getVehicleType() {

        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {

        this.vehicleType = vehicleType;
    }

    public String getColor() {

        return color;
    }

    public void setColor(String color) {

        this.color = color;
    }

    public int getOdometer() {

        return odometer;
    }

    public void setOdometer(int odometer) {

        this.odometer = odometer;
    }

    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    // Override the toString() method
    @Override
    public String toString() {
        return String.format("Vin: %-10d | Year: %-4d | Make: %-10s | Model: %-10s | Color: %-10s | Type: %-10s | Odometer: %-10d | Price: $%.2f",
                getVin(), getYear(), getMake(), getModel(), getColor(), getVehicleType(), getOdometer(), getPrice());
    }
}