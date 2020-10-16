package music.dao;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import music.model.Cart;
import music.service.CustomerOrderService;

@Repository
@Transactional
public class CartDAOImpl implements CartDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CustomerOrderService customerOrderService;

	public Cart getCartById(int cartId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return (Cart) currentSession.get(Cart.class, cartId);
	}

	public void update(Cart cart) {
		int cartId = cart.getCartId();
		double grandTotal = customerOrderService.getCustomerOrderGrandTotal(cartId);
		cart.setGrandTotal(grandTotal);
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(cart);

	}
	
	public Cart validate(int cartId) throws IOException {
		Cart cart = getCartById(cartId);
		if(cart == null || cart.getCartItems().size() == 0) {
			throw new IOException(cartId+"");
		}
		update(cart);
		return cart;
	}

}
