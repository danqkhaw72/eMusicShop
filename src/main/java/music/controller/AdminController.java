package music.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import music.dao.ProductDAO;
import music.model.Product;

@Controller
public class AdminController {
	
	private Path path, pathAudio;
	
	@Autowired
	private ProductDAO productDAO;
	
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
	public String addProductPost(@Valid @ModelAttribute("product") Product product,
									BindingResult result ,HttpServletRequest request) {
		if(result.hasErrors()) {
			return "addProduct";
		}
		
		productDAO.addProduct(product);
		
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId() + ".png");
		
		// Audio
		MultipartFile productAudio = product.getProductAudio();
		String rootDirectoryAudio = request.getSession().getServletContext().getRealPath("/");
		pathAudio = Paths.get(rootDirectoryAudio + "\\WEB-INF\\resources\\audio\\" + product.getProductId() + ".mp3");
		
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception exc) {
				exc.printStackTrace();
				throw new RuntimeException("Product image saving failed", exc);
			}
		}
		
		// Audio
		if(productAudio != null && !productAudio.isEmpty()) {
			try {
				productAudio.transferTo(new File(pathAudio.toString()));
			} catch (Exception exc) {
				exc.printStackTrace();
				throw new RuntimeException("Product audio saving failed", exc);
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
				System.out.println("delete SUCCESS !!!!!!!!!!!");
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		
		// Audio
		String rootDirectoryAudio = request.getSession().getServletContext().getRealPath("/");
		pathAudio = Paths.get(rootDirectoryAudio + "\\WEB-INF\\resources\\audio\\" + productId + ".mp3");
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);	
				System.out.println("delete SUCCESS !!!!!!!!!!!");
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		
		
		// Audio
		if(Files.exists(pathAudio)) {
			try {
				Files.delete(pathAudio);	
				System.out.println("delete SUCCESS !!!!!!!!!!!");
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		}
		
		productDAO.deleteProduct(productId);
		
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping("/admin/productInventory/editProduct/{productId}")
	public String editProduct(@PathVariable("productId") int productId, Model model) {
		
		Product product = productDAO.getProductById(productId);
		
		model.addAttribute("product", product);
		
		return "editProduct";
	}
	
	@RequestMapping(value="/admin/productInventory/editProduct", method = RequestMethod.POST)
	public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result ,
								Model model, HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "editProduct";
		}
		
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId() + ".png");
		
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(path.toString()));
			} catch (Exception e) {
				throw new RuntimeException("Product img saving failed !", e);
			}
		}
		
		// Audio
		MultipartFile productAudio = product.getProductAudio();
		String rootDirectoryAudio = request.getSession().getServletContext().getRealPath("/");
		pathAudio = Paths.get(rootDirectoryAudio + "\\WEB-INF\\resources\\audio\\" + product.getProductId() + ".mp3");
		
		
		
		if(productAudio != null && !productAudio.isEmpty()) {
			try {
				productAudio.transferTo(new File(pathAudio.toString()));
			} catch (Exception e) {
				throw new RuntimeException("Product img saving failed !", e);
			}
		}
		
		productDAO.editProduct(product);
		
		return "redirect:/admin/productInventory";
	}

}
