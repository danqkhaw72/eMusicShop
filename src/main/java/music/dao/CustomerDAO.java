package music.dao;

import java.util.List;

import music.model.Customer;

public interface CustomerDAO {
	
	void addCustomer(Customer customer);
	
	Customer getCustomerId(int customerId);
	
	List<Customer> getAllCustomer();

}
