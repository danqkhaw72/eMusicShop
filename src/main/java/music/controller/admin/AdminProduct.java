package music.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

import music.model.Product;
import music.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminProduct {
	
	private Path pathImage;
	
	private Path pathAudio;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/product/addProduct")
	public String addProduct(Model model) {
		
		Product product = new Product();
		product.setProductCategory("instrument");
		product.setProductCondition("new");
		product.setProductStatus("active");
		
		model.addAttribute("product", product);
		
		return "addProduct";
		
	}
	
	@RequestMapping(value = "/product/addProduct", method = RequestMethod.POST)
	public String addProductPost(@Valid @ModelAttribute("product") Product product,
									BindingResult result ,HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "addProduct";
		}
		
		productService.addProduct(product);
		
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		pathImage = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId() + ".png");
		
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(pathImage.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product image saving failed",e);
			}
		}
		
		MultipartFile productAudio = product.getProductAudio();
		String rootDirectoryAudio = request.getSession().getServletContext().getRealPath("/");
		pathAudio = Paths.get(rootDirectoryAudio + "\\WEB-INF\\resources\\audio\\" + product.getProductId() + ".mp3");
		
		if(productAudio != null && !productAudio.isEmpty()) {
			try {
				productAudio.transferTo(new File(pathAudio.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product audio saving failed",e);
			}
		}
		
		return "redirect:/admin/productInventory";
		
	}
	
	@RequestMapping("/product/editProduct/{productId}")
	public String editProduct(@PathVariable("productId") int productId, Model model) {
		
		Product product = productService.getProductById(productId);
		
		model.addAttribute("product", product);
		
		return "editProduct";
		
	}
	
	@RequestMapping(value = "/product/editProduct", method = RequestMethod.POST)
	public String editProductPost(@Valid @ModelAttribute("product") Product product,
									BindingResult result ,HttpServletRequest request) {
		
		if(result.hasErrors()) {
			return "editProduct";
		}
		
		
		
		MultipartFile productImage = product.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		pathImage = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId() + ".png");
		
		if(productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(pathImage.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product image saving failed",e);
			}
		}
		
		MultipartFile productAudio = product.getProductAudio();
		String rootDirectoryAudio = request.getSession().getServletContext().getRealPath("/");
		pathAudio = Paths.get(rootDirectoryAudio + "\\WEB-INF\\resources\\audio\\" + product.getProductId() + ".mp3");
		
		if(productAudio != null && !productAudio.isEmpty()) {
			try {
				productAudio.transferTo(new File(pathAudio.toString()));
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Product audio saving failed",e);
			}
		}
		
		productService.editProduct(product);
		
		return "redirect:/admin/productInventory";
		
	}
	
	@RequestMapping("/product/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId, 
								Model model, HttpServletRequest request) {
		
		String rootDirectoryAudio = request.getSession().getServletContext().getRealPath("/");
		pathAudio = Paths.get(rootDirectoryAudio + "\\WEB-INF\\resources\\audio\\" + productId + ".mp3");
		
		if(Files.exists(pathAudio)) {
			try {
				Files.delete(pathAudio);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String rootDirectoryImage = request.getSession().getServletContext().getRealPath("/");
		pathImage = Paths.get(rootDirectoryImage + "\\WEB-INF\\resources\\images\\" + productId + ".png");
		
		if(Files.exists(pathImage)) {
			try {
				Files.delete(pathImage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Product product = productService.getProductById(productId);
		productService.deleteProduct(product);
		
		return "redirect:/admin/productInventory";
		
	}
	

}
