package music.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import music.dao.CartItemDAO;
import music.model.Cart;
import music.model.CartItem;

@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemDAO cartItemDAO;

	public void addCartItem(CartItem cartItem) {
		
		cartItemDAO.addCartItem(cartItem);
	}

	public void removeCartItem(CartItem cartItem) {
		
		cartItemDAO.removeCartItem(cartItem);
	}

	public void removeAllCartItem(Cart cart) {
		
		cartItemDAO.removeAllCartItems(cart);
	}

	public CartItem getCartItemByProductId(int productId) {
		
		return cartItemDAO.getCartItemByProductId(productId);
	}

}
