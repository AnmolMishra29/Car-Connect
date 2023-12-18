package dao;
import entity.Reservation;
import exception.ReservationNotFoundException;
public interface IReservationService {

	Reservation getReservationById(int reservationId) throws ReservationNotFoundException;

	Reservation getReservationsByCustomerId(int customerId) throws ReservationNotFoundException;

	boolean createReservation(Reservation reservation);

	//boolean updateReservation(Reservation reservation);

	boolean cancelReservation(int reservationId);

}
