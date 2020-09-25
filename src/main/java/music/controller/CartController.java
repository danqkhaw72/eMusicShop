package music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import music.dao.CartDAO;
import music.dao.ProductDAO;
import music.model.Cart;

@Controller
@RequestMapping("/rest/cart")
public class CartController {
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value="cartId") Cart cartId) {
		return cartDAO.read(cartId);
	}

}
