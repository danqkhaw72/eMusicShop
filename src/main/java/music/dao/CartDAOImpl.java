package music.dao;

import java.util.HashMap;
import java.util.Map;

import music.model.Cart;

public class CartDAOImpl implements CartDAO {
	
	private Map<Integer, Cart> listOfCart;
	
	public CartDAOImpl() {
		listOfCart = new HashMap<Integer, Cart>();
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

	public Cart read(Cart cart) {
		
		return listOfCart.get(cart);
	}

	public void update(int cartId, Cart cart) {
		
		if(!listOfCart.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Cannot update  cart. A cart with the given id " +
					"does not" +
					"exists", cart.getCartId()));
		}
		
		listOfCart.put(cartId, cart);

	}

	public void delete(int cartId) {
		
		if(!listOfCart.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Cannot delete a cart. Acart with the given id " +
					"does not" +
					"exists", cartId));
		}
		
		listOfCart.remove(cartId);

	}

}
