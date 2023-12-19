package dao;
import entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Date;
import exception.AdminNotFoundException;
import util.DButil;

public class AdminService implements IAdminService {
    

    //private static final String UPDATE_ADMIN = "UPDATE admin SET username=?, password=? WHERE adminId=?";
   
	@Override
	public Admin getAdminById(int adminId) throws AdminNotFoundException {
            try(Connection connection = DButil.getConnection()){
               String SELECT_ADMIN_BY_ID = "SELECT * FROM admin WHERE adminId=?"; 
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID)) {
            preparedStatement.setInt(1, adminId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   if (resultSet.next()) {
                       return mapResultSetToAdmin(resultSet);
                   }else{
                       throw new AdminNotFoundException("Admin with ID " + adminId + " not found");
                   }
            }  
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while retrieving vehicle from the database.", e);
        }
	}

	@Override
	public Admin getAdminByUsername(String username) throws AdminNotFoundException{
            try(Connection connection = DButil.getConnection()){
               String SELECT_ADMIN_BY_USERNAME = "SELECT * FROM admin WHERE username=?";
            
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAdmin(resultSet);
                }else{
                       throw new AdminNotFoundException("Admin with Username " + username + " not found");
                   }
            }
                }
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException("An error occurred while retrieving vehicle from the database.", e);  
        }
	}

	@Override
	public boolean registerAdmin(Admin admin) {
            try(Connection connection = DButil.getConnection()){
                String INSERT_ADMIN = "INSERT INTO admin(adminId,first_name,last_name,email,phone,username, adminPassword,adminRole,joinDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN)) {
            preparedStatement.setInt(1, admin.getAdminID());
            preparedStatement.setString(2, admin.getFirstName());
            preparedStatement.setString(3, admin.getLastName());
            preparedStatement.setString(4, admin.getEmail());
            preparedStatement.setString(5, admin.getPhoneNumber());
            preparedStatement.setString(6, admin.getUsername());
            preparedStatement.setString(7, admin.getPassword());
            preparedStatement.setString(8, admin.getRole());
            preparedStatement.setString(9, admin.getJoinDate());
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
	}

//	@Override
//	public boolean updateAdmin(String adminData) {
//		System.out.println("Updated Successfully");
//		return false;
//	}

	@Override
	public boolean deleteAdmin(int adminId) {
            try(Connection connection = DButil.getConnection()){
                String DELETE_ADMIN = "DELETE FROM admin WHERE adminId=?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN)) {
            preparedStatement.setInt(1, adminId);
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
        
        
        private Admin mapResultSetToAdmin(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminID(resultSet.getInt("adminId"));
        admin.setFirstName(resultSet.getString("first_name"));
        admin.setLastName(resultSet.getString("last_name"));
        admin.setEmail(resultSet.getString("email"));
        admin.setPhoneNumber(resultSet.getString("phone"));
        admin.setUsername(resultSet.getString("username"));
        admin.setPassword(resultSet.getString("adminPassword"));
        admin.setRole(resultSet.getString("adminRole"));
        admin.setJoinDate(resultSet.getString("joinDate"));
        return admin;
         }
}
