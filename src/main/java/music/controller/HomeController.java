package music.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String viewProduct(@PathVariable String productId,
								Model model) throws IOException {
		
		Product product = productDAO.getProductById(productId);
		model.addAttribute(product);
		
		return "viewProduct";
	}
	
	@RequestMapping("/admin")
	public String adminPage() {
		return "admin";
	}
	
	@RequestMapping("/admin/productInventory")
	public String productInventory(Model model) {
		
		List<Product> products = productDAO.getAllProducts();
		model.addAttribute("products", products);
		
		return "productInventory";
		
	}
	
	@RequestMapping("/admin/productInventory/addProduct")
	public String addProduct(Model model) {
		
		Product product = new Product();
		product.setProductCategory("instrument");
		product.setProductCondition("new");
		product.setProductStatus("active");
		
		model.addAttribute("product", product);
		
		return "addProduct";
		
	}
	
	@RequestMapping(value="/admin/productInventory/addProduct", method = RequestMethod.POST)
	public String addProductPost(@ModelAttribute("product") Product product) {
		productDAO.addProduct(product);
		
		return "redirect:/admin/productInventory";
	}

}
