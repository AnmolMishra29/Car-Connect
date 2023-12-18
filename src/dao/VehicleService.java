package dao;
import entity.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import exception.VehicleNotFoundException;
import util.DButil;

public  class VehicleService implements IVehicleService {
       
    //private static final String GET_AVAILABLE_VEHICLES = "SELECT count(vehicleId) FROM vehicle WHERE availability = true";
    //private static final String UPDATE_VEHICLE = "UPDATE vehicle SET username=?, password=? WHERE adminId=?";
   
     
       @Override
	public Vehicle getVehiclebyId(int vehicleId) throws VehicleNotFoundException  {
            try(Connection connection = DButil.getConnection()){
               String SELECT_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE vehicleId=?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VEHICLE_BY_ID)) {
            preparedStatement.setInt(1, vehicleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToVehicle(resultSet);
                }else{
                    throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found");
                }
            }            
            }
        } 
            catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while retrieving vehicle from the database.", e);
        }
	}

//	@Override
//	public boolean getAvailableVehicles() {
//            List<Vehicle> vehicles = new ArrayList<>();
//
//            try (PreparedStatement statement = connection.prepareStatement(GET_AVAILABLE_VEHICLES);
//             ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                Vehicle vehicle = new Vehicle();
//                // Set vehicle properties from the result set
//                vehicle.setVehicleID(resultSet.getInt("vehicleId"));
//                vehicle.setModel(resultSet.getString("model"));
//                vehicle.setMake(resultSet.getString("make"));
//                vehicle.setYear(resultSet.getInt("year"));
//                vehicle.setColor(resultSet.getString("color"));
//                vehicle.setRegistrationNumber(resultSet.getString("registrationNumber"));
//                vehicle.setAvailability(resultSet.getBoolean("availability"));
//                vehicle.setDailyRate(resultSet.getDouble("dailyRate"));
//
//                vehicles.add(vehicle);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace(); // Handle exceptions properly in a real application
//        }
//               System.out.println("total vehicles");
//		return false;
//	}

	@Override
	public boolean addVehicle(Vehicle vehicle) {
            try(Connection connection = DButil.getConnection()){
                  String INSERT_VEHICLE = "INSERT INTO vehicle(vehicleId, model, make,yearr,color,registration_number,availability,dailyrate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
            
	    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEHICLE)) {
            preparedStatement.setInt(1, vehicle.getVehicleID());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setString(6, vehicle.getRegistrationNumber());
            preparedStatement.setBoolean(7, vehicle.getAvailability());
            preparedStatement.setDouble(8, vehicle.getDailyRate());
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
//	public boolean updateVehicle(Vehicle vehicle) {
//                System.out.println("Implemented");
//		return false;
//	}

	@Override
	public boolean removeVehicle(int vehicleId) {
            try(Connection connection = DButil.getConnection()){
                String DELETE_VEHICLE = "DELETE FROM vehicle WHERE vehicleId=?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE)) {
            preparedStatement.setInt(1, vehicleId);
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            return false ;
        }
	}
        
        private Vehicle mapResultSetToVehicle(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleID(resultSet.getInt("vehicleId"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setYear(resultSet.getInt("yearr"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setRegistrationNumber(resultSet.getString("registration_number"));
        vehicle.setAvailability(resultSet.getBoolean("availability"));
        vehicle.setDailyRate(resultSet.getDouble("dailyrate"));
        return vehicle;
         }
}
