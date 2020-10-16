package music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import music.dao.CustomerOrderDAO;
import music.model.Cart;
import music.model.CartItem;
import music.model.CustomerOrder;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDAO customerOrderDAO;
	
	@Autowired
	private CartService cartService;
	
	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderDAO.addCustomerOrder(customerOrder);

	}

	public double getCustomerOrderGrandTotal(int cartId) {
		
		double grandTotal=0;
		Cart cart = cartService.getCartById(cartId);
		List<CartItem> cartItems = cart.getCartItems();
		
		for(CartItem item: cartItems) {
			grandTotal+=item.getTotalPrice();
		}
		
		
		return grandTotal;
	}

}
