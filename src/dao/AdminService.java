package dao;
import entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Date;
import exception.AdminNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdminService implements IAdminService {

    private static final String SELECT_ADMIN_BY_ID = "SELECT * FROM admin WHERE adminId=?";
    private static final String SELECT_ADMIN_BY_USERNAME = "SELECT * FROM admin WHERE username=?";
    private static final String INSERT_ADMIN = "INSERT INTO admin(adminId,first_name,last_name,email,phone,username, adminPassword,adminRole,joinDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //private static final String UPDATE_ADMIN = "UPDATE admin SET username=?, password=? WHERE adminId=?";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE adminId=?";

    private final Connection connection;
    //private int adminId;

     public AdminService(Connection connection) {
        this.connection = connection;
    }

    public AdminService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	@Override
	public Admin getAdminById(int adminId) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID)) {
            preparedStatement.setInt(1, adminId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                   if (resultSet.next()) {
                       return mapResultSetToAdmin(resultSet);
                   }else{
                       throw new AdminNotFoundException("Admin with ID " + adminId + " not found");
                   }
            }   catch (AdminNotFoundException ex) {
                       Logger.getLogger(AdminService.class.getName()).log(Level.SEVERE, null, ex);
                }
        } catch (SQLException e) {
            // Handle exceptions (log, throw custom exceptions, etc.)
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Admin getAdminByUsername(String username) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAdmin(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions (log, throw custom exceptions, etc.)
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public boolean registerAdmin(Admin admin) {
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
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public Admin updateAdmin(String adminData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAdmin(int adminId) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN)) {
            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
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
