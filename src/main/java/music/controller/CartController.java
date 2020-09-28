package music.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import music.dao.CartDAO;
import music.dao.ProductDAO;
import music.model.Cart;
import music.model.CartItem;
import music.model.Product;

@Controller
@RequestMapping("/rest/cart")
public class CartController {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value="cartId") String cartId) {
		return cartDAO.read(cartId);
	}
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT) 
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart) {
		cartDAO.update(cartId, cart);
	}
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE) 
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		cartDAO.delete(cartId);
	}
	
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT) 
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable(value = "productId") int productId, HttpServletRequest request) {
		
		String sessionId = request.getSession(true).getId();
		Cart cart =  cartDAO.read(sessionId);
		
		if(cart == null) {
			cart = cartDAO.create(new Cart(sessionId));
		}
		
		Product product = productDAO.getProductById(productId);
		if(product == null) {
			throw new IllegalArgumentException(new Exception());
		}
		
		cart.addCartItem(new CartItem(product));
		
		cartDAO.update(sessionId, cart);
		
	}
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT) 
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable(value = "productId") int productId, HttpServletRequest request) {
		
		String sessionId = request.getSession(true).getId();
		Cart cart =  cartDAO.read(sessionId);
		
		if(cart == null) {
			cart = cartDAO.create(new Cart(sessionId));
		}
		
		Product product = productDAO.getProductById(productId);
		if(product == null) {
			throw new IllegalArgumentException(new Exception());
		}
		
		cart.removeCartItem(new CartItem(product));
		
		cartDAO.update(sessionId, cart);
		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify you payload")
	public void handleClientError(Exception exc) {
		
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internet server error")
	public void handleServerError(Exception exc) {
		
	}

}
