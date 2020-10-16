package music.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import music.model.CustomerOrder;

@Repository
@Transactional
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addCustomerOrder(CustomerOrder customerOrder) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customerOrder);
		currentSession.flush();

	}

}
