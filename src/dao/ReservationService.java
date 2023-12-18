package dao;
import entity.Reservation;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import exception.ReservationNotFoundException;
import util.DButil;

public class ReservationService implements IReservationService {

    //private static final String UPDATE_RESERVATION = "UPDATE users SET username=?, password=? WHERE adminId=?";
    
	@Override
	public Reservation getReservationById(int reservationId) throws ReservationNotFoundException {
            try(Connection connection = DButil.getConnection()){
               String SELECT_RESERVATION_BY_ID = "SELECT * FROM reservation  WHERE reservationId=?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATION_BY_ID)) {
            preparedStatement.setInt(1, reservationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToReservation(resultSet);
                }else{
                       throw new ReservationNotFoundException("Reservation with ID " + reservationId + " not found");
                   }
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while retrieving vehicle from the database.", e);
        }
	}

	@Override
	public Reservation getReservationsByCustomerId(int customerId)  throws ReservationNotFoundException {
            try(Connection connection = DButil.getConnection()){
                String SELECT_RESERVATION_BY_CUSTOMERID = "SELECT * FROM reservation WHERE customerId=?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATION_BY_CUSTOMERID)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToReservation(resultSet);
                }else{
                       throw new ReservationNotFoundException("Reservation with Customer ID " + customerId + " not found");
                   }
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while retrieving vehicle from the database.", e);     
        }
	}

        
	@Override
	public boolean createReservation(Reservation reservation) {
            try(Connection connection = DButil.getConnection()){
               String INSERT_RESERVATION = "INSERT INTO reservation(reservationId, customerId,vehicleId,startDate,endDate,totalCost,current_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
	    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION)) {
            preparedStatement.setInt(1, reservation.getReservationID());
            preparedStatement.setInt(2, reservation.getCustomerID());
            preparedStatement.setInt(3, reservation.getVehicleID());
            preparedStatement.setString(4, reservation.getStartDate());
            preparedStatement.setString(5, reservation.getEndDate());
            preparedStatement.setDouble(6, reservation.getTotalCost());
            preparedStatement.setString(7, reservation.getStatus());
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
//	public boolean updateReservation(Reservation reservation) {
//                System.out.println("Updated Successfully");
//		return false;
//	}

	@Override
	public boolean cancelReservation(int reservationId) {
            try(Connection connection = DButil.getConnection()){
                String DELETE_RESERVATION = "DELETE FROM reservation WHERE reservationId=?";
            
	    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION)) {
            preparedStatement.setInt(1, reservationId);
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
        
        
        private Reservation mapResultSetToReservation(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setVehicleID(resultSet.getInt("reservationId"));
        reservation.setCustomerID(resultSet.getInt("customerId"));
        reservation.setVehicleID(resultSet.getInt("vehicleId"));
        reservation.setStartDate(resultSet.getString("startDate"));
        reservation.setEndDate(resultSet.getString("endDate"));
        reservation.setTotalCost(resultSet.getDouble("totalCost"));
        reservation.setStatus(resultSet.getString("current_status"));
        return reservation;
         }
}
