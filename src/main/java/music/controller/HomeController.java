package music.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import music.dao.ProductDAO;
import music.model.Product;

@Controller
public class HomeController {
	
	private Path path;
	
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
	public String addProductPost(@ModelAttribute("product") Product product,
									HttpServletRequest request) {
		productDAO.addProduct(product);
		
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId() + ".png");
		
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception exc) {
				exc.printStackTrace();
				throw new RuntimeException("Product image saving failed", exc);
			}
		}
		
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping("/admin/productInventory/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable int productId,
								Model model, HttpServletRequest request) {
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + productId + ".png");
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		
		productDAO.deleteProduct(productId);
		
		return "redirect:/admin/productInventory";
	}

}
