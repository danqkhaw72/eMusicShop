package music.dao;

import music.model.Cart;
import music.model.CartItem;

public interface CartItemDAO {
	
	void addCartItem(CartItem cartItem);
	
	void removeCartItem(CartItem cartItem);
	
	void removeAllCartItems(Cart cart);
	
	CartItem getCartItemByProductId(int productId);

}
