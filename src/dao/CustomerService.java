package dao;
import entity.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService implements ICustomerService {

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE adminId=?";
    private static final String SELECT_CUSTOMER_BY_USERNAME = "SELECT * FROM customer WHERE username=?";
    private static final String INSERT_CUSTOMER = "INSERT INTO customer(customerId,firstname,lastname,email,contact,address,username, password,registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //private static final String UPDATE_CUSTOMER = "UPDATE users SET username=?, password=? WHERE adminId=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customerId=?";

    private final Connection connection;
    //private int adminId;

     public CustomerService(Connection connection) {
        this.connection = connection;
    }

    public CustomerService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	@Override
	public Customer getCustomerById(int customerId) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions (log, throw custom exceptions, etc.)
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Customer getCustomerByUsername(String username) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions (log, throw custom exceptions, etc.)
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public boolean registerCustomer(Customer customer) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getUsername());
            preparedStatement.setString(8, customer.getPassword());
            preparedStatement.setDate(9, (Date) customer.getRegistrationDate());
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public Customer updateCustomer(Customer customerData) {
		// TODO Auto-generated method stub
		return null;
	}

	
        @Override
	public boolean deleteCustomer(int customerId) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            return false;
        }
	}
        
        private Customer mapResultSetToCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustomerID(resultSet.getInt("customerId"));
        customer.setFirstName(resultSet.getString("firstname"));
        customer.setLastName(resultSet.getString("lastname"));
        customer.setEmail(resultSet.getString("email"));
        customer.setPhoneNumber(resultSet.getString("contact"));
        customer.setAddress(resultSet.getString("address"));
        customer.setUsername(resultSet.getString("username"));
        customer.setPassword(resultSet.getString("password"));
        customer.setRegistrationDate(resultSet.getDate("registrationDate"));
        return customer;
         }

}
