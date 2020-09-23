package music.dao;

import java.util.List;

import music.model.Product;

public interface ProductDAO {
	
	void addProduct(Product product);
	
	Product getProductById(String id);
	
	List<Product> getAllProducts();
	
	void deleteProduct(String id);

}
