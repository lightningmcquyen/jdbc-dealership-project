package com.pluralsight.dealership;

import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();

        String url = "jdbc:mysql://localhost:3306/car_dealership";
        dataSource.setUsername(args[0]);
        dataSource.setPassword(args[1]);
        dataSource.setUrl(url);

        UserInterface ui = new UserInterface(dataSource);
        ui.display(); // Start the application
    }
}