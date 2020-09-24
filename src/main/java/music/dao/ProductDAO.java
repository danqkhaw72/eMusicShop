package music.dao;

import java.util.List;

import music.model.Product;

public interface ProductDAO {
	
	void addProduct(Product product);
	
	Product getProductById(int id);
	
	List<Product> getAllProducts();
	
	void deleteProduct(int id);

}
