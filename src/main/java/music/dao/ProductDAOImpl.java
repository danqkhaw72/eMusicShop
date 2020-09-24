package music.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import music.model.Product;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	
	private SessionFactory sessionFactory;

	public void addProduct(Product product) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(product);
		currentSession.flush();

	}

	public Product getProductById(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Product product = (Product) currentSession.get(Product.class, id);
		currentSession.flush();
		
		return product;
	}

	public List<Product> getAllProducts() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("from Product");
		List<Product> products = query.list();
		currentSession.flush();
		
		return products;
	}

	public void deleteProduct(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(getProductById(id));
		currentSession.flush();

	}


}
