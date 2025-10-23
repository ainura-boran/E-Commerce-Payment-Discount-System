package repository;

import app.DataBase;
import store.Customer;

import java.sql.*;
import java.util.Optional;

public class JdbcCustomerRepository implements CustomerRepository {
    private final DataBase db;

    public JdbcCustomerRepository(DataBase db) {
        this.db = db;
    }

    @Override
    public int save(Customer customer) {
        String sql = "INSERT INTO customers (name, email, address, points) VALUES (?,?,?,?) RETURNING id";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getAddress());
            ps.setInt(4, customer.getPoints());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
                throw new SQLException("INSERT did not return id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("saveCustomer failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Customer> findById(int id) {
        String sql = "SELECT name, email, address, points FROM customers WHERE id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getInt("points")
                    );
                    return Optional.of(customer);
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("findCustomerById failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void addPoints(int customerId, int delta) {
        String sql = "UPDATE customers SET points = points + ? WHERE id = ?";
        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, delta);
            ps.setInt(2, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("addPoints failed: " + e.getMessage(), e);
        }
    }
}
