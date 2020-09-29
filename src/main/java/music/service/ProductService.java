package music.service;

import java.util.List;

import music.model.Product;

public interface ProductService {
	
	List<Product> getProductList();
	
	Product getProductById(int id);
	
	void addProduct(Product product);
	
	void editProduct(Product product);
	
	void deleteProduct(Product product);

}
