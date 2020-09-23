package music.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import music.model.Product;

public class ProductDAO {
	
	private List<Product> productList;
	
	public List<Product> getProductList() {
		
		Product products = new Product();
		
		products.setProductId("0");
		products.setProductName("Violin");
		products.setProductCategory("Instrucment");
		products.setProductDescription("This is a fender");
		products.setProductPrice(1200);
		products.setProductCondition("new");
		products.setProductStatus("Active");
		products.setUnitInStock(11);
		products.setProductManufacturer("Fender");
		
		productList = new ArrayList<Product>();
		productList.add(products);

		
		Product products1 = new Product();
		
		products1.setProductId("01");
		products1.setProductName("Gitar");
		products1.setProductCategory("Instrucment");
		products1.setProductDescription("This is a fender");
		products1.setProductPrice(1000);
		products1.setProductCondition("new");
		products1.setProductStatus("Active");
		products1.setUnitInStock(11);
		products1.setProductManufacturer("Fender");
		
		
		productList.add(products1);
		
		
		Product products2 = new Product();
		
		products2.setProductId("02");
		products2.setProductName("Piano");
		products2.setProductCategory("Instrucment");
		products2.setProductDescription("This is a piano");
		products2.setProductPrice(2540);
		products2.setProductCondition("new");
		products2.setProductStatus("Active");
		products2.setUnitInStock(11);
		products2.setProductManufacturer("Fender");
		
		
		productList.add(products2);
		
		return productList;
		
	}
	
	public Product getProductById(String productId) throws IOException{
		
		for(Product product: getProductList()) {
			if(product.getProductId().equals(productId)) {
				return product;
			}
		}
		
		throw new IOException("No product found");
		
	}

}
