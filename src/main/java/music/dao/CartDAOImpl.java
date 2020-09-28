package music.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import music.model.Cart;

@Repository
public class CartDAOImpl implements CartDAO {
	
	private Map<String, Cart> listOfCart;
	
	public CartDAOImpl() {
		listOfCart = new HashMap<String, Cart>();
	}

	public Cart create(Cart cart) {
		
		if(listOfCart.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String.format("Cannot create a cart. A cart with the given id " +
					"already" +
					"exists", cart.getCartId()));
		}
		
		listOfCart.put(cart.getCartId(), cart);
		
		return cart;
	}

	public Cart read(String cartId) {
		
		return listOfCart.get(cartId);
	}

	public void update(String cartId, Cart cart) {
		
		if(!listOfCart.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Cannot update  cart. A cart with the given id " +
					"does not" +
					"exists", cart.getCartId()));
		}
		
		listOfCart.put(cartId, cart);

	}

	public void delete(String cartId) {
		
		if(!listOfCart.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Cannot delete a cart. Acart with the given id " +
					"does not" +
					"exists", cartId));
		}
		
		listOfCart.remove(cartId);

	}

}
