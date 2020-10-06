package music.service;

import java.util.List;

import music.model.Customer;

public interface CustomerService {
	
	void addCustomer(Customer customer);
	
	Customer getCustomerId(int customerId);
	
	List<Customer> getAllCustomer();

}
