package music.service;

import music.model.Cart;

public interface CartService {
	
	Cart getCartById(int cartId);
	
	void update(Cart cart);

}
