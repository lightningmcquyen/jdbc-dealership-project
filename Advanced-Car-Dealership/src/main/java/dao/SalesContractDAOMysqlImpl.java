package dao;

import com.pluralsight.dealership.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesContractDAOMysqlImpl implements SalesContractDAO {
    private final DataSource dataSource;

    public SalesContractDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveSalesContract(SalesContract salesContract) {
        String query = """
                INSERT INTO sales_contracts (dateOfContract, customerName, customerEmail, vin, originalPrice, financed)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDate(1, new java.sql.Date(System.currentTimeMillis())); // Current date
            ps.setString(2, salesContract.getCustomerName());
            ps.setString(3, salesContract.getCustomerEmail());
            ps.setInt(4, salesContract.getVin());
            ps.setDouble(5, salesContract.getOriginalPrice());
            ps.setBoolean(6, salesContract.isFinanced());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving sales contract", e);
        }
    }
}
