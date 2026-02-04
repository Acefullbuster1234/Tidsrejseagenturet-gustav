package database;

import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public List<Customer> initializeCustomer() throws SQLException {
        List<Customer> Customers = new ArrayList<>();
        String sql = "SELECT id, name, email FROM Customer";

        try(Connection conn = databasecon.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Customer customer = createCustomerFromResultSet(rs);
                if(customer != null) {
                    Customers.add(customer);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("failed to initialize customers");
        }
        return Customers;
    }
    private Customer createCustomerFromResultSet(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String email = rs.getString("email");
        Integer id = rs.getInt("id");

        return new Customer(id, name, email);
    }

    public void saveCustomer(String Name, String Email) throws SQLException {
        String sql = "INSERT INTO customer (Name, Email) VALUES (?,?)";
        try(Connection conn = databasecon.getConnection()){
            conn.setAutoCommit(false);
            try(PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,Name);
                pstmt.setString(2,Email);
                pstmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("save failed");
        }
    }
    private int getCustomerId(Connection conn, String customername) throws SQLException {
        String selectSQL = "SELECT id FROM customer WHERE name = ?";
        try(PreparedStatement stmt = conn.prepareStatement(selectSQL)) {
            stmt.setString(1,customername);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        String insertSQL = "INSERT INTO customer (name,email) VALUES (?)";
        try(PreparedStatement stmt = conn.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, customername);
            stmt.executeUpdate();
            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    return rs.getInt(1);
                }
                else {
                    throw new SQLException("could not generate new customer");
                }
            }
        }
    }
}

