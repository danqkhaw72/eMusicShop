package music.dao;

import music.model.Cart;

public interface CartDAO {
	
	Cart create(Cart cart);
	
	Cart read(Cart cart);
	
	void update(int cartId, Cart cart);
	
	void delete(int cartId);

}
