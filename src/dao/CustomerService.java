package dao;
import entity.Customer;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exception.CustomerNotFoundException;
import util.DButil;

public class CustomerService implements ICustomerService {
    
    //private static final String UPDATE_CUSTOMER = "UPDATE users SET username=?, password=? WHERE adminId=?";
    
    
	@Override
	public Customer getCustomerById(int customerId) throws CustomerNotFoundException{
            try(Connection connection = DButil.getConnection()){
              String SELECT_CUSTOMER_BY_ID = "SELECT * FROM customer WHERE adminId=?";  
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }else{
                       throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
                   }
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while retrieving vehicle from the database.", e);
        }
	}

	@Override
	public Customer getCustomerByUsername(String username) throws CustomerNotFoundException {
            try(Connection connection = DButil.getConnection()){
               String SELECT_CUSTOMER_BY_USERNAME = "SELECT * FROM customer WHERE username=?";
            
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCustomer(resultSet);
                }else{
                       throw new CustomerNotFoundException("Customer with Username " + username + " not found");
                   }
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while retrieving customer from the database.", e);
        }
	}

	@Override
	public boolean registerCustomer(Customer customer) {
            try(Connection connection = DButil.getConnection()){
               String INSERT_CUSTOMER = "INSERT INTO customer(customerId,firstname,lastname,email,contact,address,username, password,registrationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFirstName());
            preparedStatement.setString(3, customer.getLastName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setString(7, customer.getUsername());
            preparedStatement.setString(8, customer.getPassword());
            preparedStatement.setString(9,   customer.getRegistrationDate());
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

//	@Override
//	public boolean updateCustomer(Customer customerData) {
//                System.out.println("Updated Successfully");
//		return false;
//	}

	
        @Override
	public boolean deleteCustomer(int customerId) {
            try(Connection connection = DButil.getConnection()){
               String DELETE_CUSTOMER = "DELETE FROM customer WHERE customerId=?"; 
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
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
        customer.setRegistrationDate(resultSet.getString("registrationDate"));
        return customer;
         }
}
