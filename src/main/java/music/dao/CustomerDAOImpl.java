package music.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import music.model.Authorities;
import music.model.Cart;
import music.model.Customer;
import music.model.Users;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		customer.getBillingAddress().setCustomer(customer);
		customer.getShippingAddress().setCustomer(customer);
		
		currentSession.saveOrUpdate(customer);
		currentSession.saveOrUpdate(customer.getBillingAddress());
		currentSession.saveOrUpdate(customer.getShippingAddress());
		
		Users newUsers = new Users();
		newUsers.setUsername(customer.getUsername());
		newUsers.setPassword(customer.getPassword());
		newUsers.setEnabled(true);
		newUsers.setCustomerId(customer.getCustomerId());
		
		Authorities authorities = new Authorities();
		authorities.setUsername(customer.getUsername());
		authorities.setAuthority("ROLE_USER");
		currentSession.saveOrUpdate(newUsers);
		currentSession.saveOrUpdate(authorities);
		
		Cart cart = new Cart();
		cart.setCustomer(customer);
		customer.setCart(cart);
		currentSession.saveOrUpdate(customer);
		currentSession.saveOrUpdate(cart);
		
		currentSession.flush();
		

	}

	public Customer getCustomerId(int customerId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		return (Customer) currentSession.get(Customer.class, customerId);
	}

	public List<Customer> getAllCustomer() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from Customer");
		List<Customer> customerList = query.list();
		
		return customerList;
	}

	public Customer getCustomerByUsername(String username) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from Customer where username = ?");
		query.setString(0, username);
		
		return (Customer) query.uniqueResult();
	}

}
