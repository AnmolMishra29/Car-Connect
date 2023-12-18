package dao;
import entity.Reservation;

public interface IReservationService {

	Reservation getReservationById(int reservationId);

	Reservation getReservationsByCustomerId(int customerId);

	boolean createReservation(Reservation reservation);

	Reservation updateReservation(Reservation reservation);

	boolean cancelReservation(int reservationId);

}
