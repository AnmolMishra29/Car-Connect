package dao;
import entity.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationService implements IReservationService {

    private static final String SELECT_RESERVATION_BY_ID = "SELECT * FROM reservation  WHERE reservationId=?";
    private static final String SELECT_RESERVATION_BY_CUSTOMERID = "SELECT * FROM reservation WHERE customerId=?";
    private static final String INSERT_RESERVATION = "INSERT INTO reservation(reservationId, customerId,vehicleId,startDate,endDate,totalCost,current_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    //private static final String UPDATE_RESERVATION = "UPDATE users SET username=?, password=? WHERE adminId=?";
    private static final String DELETE_RESERVATION = "DELETE FROM reservation WHERE reservationId=?";

    private final Connection connection;

     public ReservationService(Connection connection) {
        this.connection = connection;
    }

    public ReservationService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
	@Override
	public Reservation getReservationById(int reservationId) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATION_BY_ID)) {
            preparedStatement.setInt(1, reservationId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToReservation(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions (log, throw custom exceptions, etc.)
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Reservation getReservationsByCustomerId(int customerId) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESERVATION_BY_CUSTOMERID)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToReservation(resultSet);
                }
            }
        } catch (SQLException e) {
            // Handle exceptions (log, throw custom exceptions, etc.)
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public boolean createReservation(Reservation reservation) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESERVATION)) {
            preparedStatement.setInt(1, reservation.getReservationID());
            preparedStatement.setInt(2, reservation.getCustomerID());
            preparedStatement.setInt(3, reservation.getVehicleID());
            preparedStatement.setDate(4, (Date) reservation.getStartDate());
            preparedStatement.setDate(5, (Date) reservation.getEndDate());
            preparedStatement.setDouble(6, reservation.getTotalCost());
            preparedStatement.setString(7, reservation.getStatus());
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public Reservation updateReservation(Reservation reservation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelReservation(int reservationId) {
	    try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESERVATION)) {
            preparedStatement.setInt(1, reservationId);
            preparedStatement.executeUpdate();
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
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
        reservation.setStartDate(resultSet.getDate("startDate"));
        reservation.setEndDate(resultSet.getDate("endDate"));
        reservation.setTotalCost(resultSet.getDouble("totalCost"));
        reservation.setStatus(resultSet.getString("current_status"));
        return reservation;
         }

}
