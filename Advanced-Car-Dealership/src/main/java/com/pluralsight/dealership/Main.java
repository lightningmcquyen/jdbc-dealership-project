package com.pluralsight.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();

        String url = "jdbc:mysql://localhost:3306/car_dealership";
        dataSource.setUsername(args[0]); // Assumes username is the first argument
        dataSource.setPassword(args[1]); // Assumes password is the second argument
        dataSource.setUrl(url);

        UserInterface ui = new UserInterface(dataSource);
        ui.display(); // Start the application
    }
}