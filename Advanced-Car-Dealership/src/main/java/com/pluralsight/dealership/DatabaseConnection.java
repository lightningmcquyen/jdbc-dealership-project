package com.pluralsight.dealership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/car_dealership";
    private static final String USER = "root";
    private static final String PASSWORD = "YU_oppdivide!20";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

