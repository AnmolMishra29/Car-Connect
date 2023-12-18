package dao;
import entity.Vehicle;
import exception.VehicleNotFoundException;

public interface IVehicleService {

	Vehicle getVehiclebyId(int vehicleId) throws VehicleNotFoundException;

	//boolean getAvailableVehicles();

	boolean addVehicle(Vehicle vehicle);

	//boolean updateVehicle(Vehicle vehicle);

	boolean removeVehicle(int vehicleId);

}