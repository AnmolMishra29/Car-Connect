package dao;
import entity.Vehicle;

public interface IVehicleService {

	Vehicle getVehiclebyId(int vehicleId);

	Vehicle getAvailableVehicles();

	boolean addVehicle(Vehicle vehicle);

	Vehicle updateVehicle(Vehicle vehicle);

	boolean removeVehicle(int vehicleId);

}