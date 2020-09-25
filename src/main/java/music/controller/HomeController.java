package music.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import music.dao.ProductDAO;
import music.model.Product;

@Controller
public class HomeController {
	
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/productList")
	public String getProducts(Model model) {
		
		List<Product> products = productDAO.getAllProducts();
		
		model.addAttribute("products", products);
		
		return "productList";
		
	}
	
	@RequestMapping("/productList/viewProduct/{productId}")
	public String viewProduct(@PathVariable int productId,
								Model model) throws IOException {
		
		Product product = productDAO.getProductById(productId);
		model.addAttribute(product);
		
		return "viewProduct";
	}
	
	

}
