package music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import music.dao.ProductDAO;
import music.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	public List<Product> getProductList() {
		
		return productDAO.getProductList();
	}

	public Product getProductById(int productId) {
		
		return productDAO.getProductById(productId);
	}

	public void addProduct(Product product) {
		
		productDAO.addProduct(product);

	}

	public void editProduct(Product product) {
		
		productDAO.editProduct(product);

	}

	public void deleteProduct(Product product) {
		
		productDAO.deleteProduct(product);

	}

}
