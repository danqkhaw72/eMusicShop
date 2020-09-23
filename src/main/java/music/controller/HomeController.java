package music.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import music.dao.ProductDAO;
import music.model.Product;

@Controller
public class HomeController {
	
	private ProductDAO productDAO = new ProductDAO();
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/productList")
	public String getProducts(Model model) {
		
		List<Product> products = productDAO.getProductList();
		
		model.addAttribute("products", products);
		
		return "productList";
		
	}
	
	@RequestMapping("/productList/viewProduct/{productId}")
	public String viewProduct(@PathVariable String productId,
								Model model) throws IOException {
		
		Product product = productDAO.getProductById(productId);
		model.addAttribute(product);
		
		return "viewProduct";
	}

}
