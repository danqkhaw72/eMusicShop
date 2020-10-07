package music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import music.dao.CustomerDAO;
import music.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	public void addCustomer(Customer customer) {
		
		customerDAO.addCustomer(customer);
		
	}

	public Customer getCustomerId(int customerId) {
		
		return customerDAO.getCustomerId(customerId);
	}

	public List<Customer> getAllCustomer() {
		
		return customerDAO.getAllCustomer();
	}

	public Customer getCustomerByUsername(String username) {
		
		return customerDAO.getCustomerByUsername(username);
	}

}
