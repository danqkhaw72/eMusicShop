package music.dao;

import music.model.Cart;

public interface CartDAO {
	
	Cart create(Cart cart);
	
	Cart read(String cart);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);

}
