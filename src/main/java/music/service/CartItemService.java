package music.service;

import music.model.Cart;
import music.model.CartItem;

public interface CartItemService {
	
	void addCartItem(CartItem cartItem);
	
	void removeCartItem(CartItem cartItem);
	
	void removeAllCartItem(Cart cart);
	
	CartItem getCartItemByProductId(int productId);

}
