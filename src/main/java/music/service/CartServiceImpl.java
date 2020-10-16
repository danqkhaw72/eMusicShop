package music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import music.dao.CartDAO;
import music.model.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;
	
	public Cart getCartById(int cartId) {
		
		return cartDAO.getCartById(cartId);
	}

	public void update(Cart cart) {
		cartDAO.update(cart);

	}

}
