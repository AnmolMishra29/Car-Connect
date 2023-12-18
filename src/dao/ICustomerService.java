package dao;
import entity.Customer;

public interface ICustomerService {

	Customer getCustomerById(int customerId);

	Customer getCustomerByUsername(String username);

	boolean registerCustomer(Customer customer);

	Customer updateCustomer(Customer customerData);

	boolean deleteCustomer(int customerId);
}
