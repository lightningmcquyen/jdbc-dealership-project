package dao;

import com.pluralsight.dealership.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Add a new vehicle to the database
    public void addVehicle(Vehicle vehicle) {
        String query = """
                INSERT INTO vehicles (vin, year, make, model, vehicleType, color, odometer, price, sold)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, vehicle.getVin());
            statement.setInt(2, vehicle.getYear());
            statement.setString(3, vehicle.getMake());
            statement.setString(4, vehicle.getModel());
            statement.setString(5, vehicle.getVehicleType());
            statement.setString(6, vehicle.getColor());
            statement.setInt(7, vehicle.getOdometer());
            statement.setDouble(8, vehicle.getPrice());
            statement.setBoolean(9, false); // Set sold to false by default

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("Failed to add vehicle. No rows affected.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding vehicle", e);
        }
    }

    // Remove a vehicle from the database by VIN
    public void removeVehicle(int vin) {
        String query = """
                DELETE FROM vehicles
                WHERE vin = ?;
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, vin);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new RuntimeException("Failed to remove vehicle. VIN not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error removing vehicle", e);
        }
    }

    public List<Vehicle> findByPriceRange(double minPrice, double maxPrice) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = """
                SELECT * 
                FROM vehicles 
                WHERE price BETWEEN ? AND ?;
                """;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                vehicles.add(vehicleResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by price range", e);
        }

        return vehicles;
    }

    public List<Vehicle> findByMakeAndModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = """
                SELECT * 
                FROM vehicles 
                WHERE make = ? AND model = ?;
                """;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, make);
            statement.setString(2, model);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                vehicles.add(vehicleResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by make and model", e);
        }

        return vehicles;
    }

    public List<Vehicle> findByYearRange(int minYear, int maxYear) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = """
                SELECT * 
                FROM vehicles 
                WHERE year BETWEEN ? AND ?;
                """;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, minYear);
            statement.setInt(2, maxYear);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                vehicles.add(vehicleResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by year range", e);
        }

        return vehicles;
    }

    public List<Vehicle> findByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = """
                SELECT * 
                FROM vehicles 
                WHERE color = ?;
                """;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, color);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                vehicles.add(vehicleResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by color", e);
        }

        return vehicles;
    }

    public List<Vehicle> findByMileageRange(double minMileage, double maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = """
                SELECT * 
                FROM vehicles 
                WHERE odometer BETWEEN ? AND ?;
                """;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, minMileage);
            statement.setDouble(2, maxMileage);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                vehicles.add(vehicleResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by mileage range", e);
        }

        return vehicles;
    }

    public List<Vehicle> findByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();

        String query = """
                SELECT * 
                FROM vehicles 
                WHERE vehicleType = ?;
                """;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                vehicles.add(vehicleResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vehicles by type", e);
        }

        return vehicles;
    }

    private Vehicle vehicleResultSet (ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getInt("vin"),
                rs.getInt("year"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("vehicleType"),
                rs.getString("color"),
                rs.getInt("odometer"),
                rs.getDouble("price")
        );
    }
}