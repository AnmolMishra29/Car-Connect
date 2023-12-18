package dao;
import entity.Customer;
import exception.CustomerNotFoundException;

public interface ICustomerService {

	Customer getCustomerById(int customerId)throws CustomerNotFoundException;

	Customer getCustomerByUsername(String username)throws CustomerNotFoundException;

	boolean registerCustomer(Customer customer);

	//boolean updateCustomer(Customer customerData);

	boolean deleteCustomer(int customerId);
}
