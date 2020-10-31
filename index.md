## Music Store

### Kiến trúc
![](https://terasolunaorg.github.io/guideline/1.0.1.RELEASE/en/_images/RequestLifecycle.png)
Kiến trúc MVC (Model - View - Controller) thể hiện rõ trong sơ đồ này. Nếu chưa hiểu thì các bạn chỉ cần tập trung 4 thành phần sau:
1. **Controller**: Thành phần điều phối. Khi request https://url/card/ **(từ 1 -> 2-> 3 -> 4)** sẽ chạy vào controller CardController (lấy ví dụ) chẳng hạn. Nó sẽ lấy data từ database và return **view name** (5) jsp cho user thấy ( tương tự với controller trong servelet) 
2. **Model**: Khai báo các thuộc tính (như Xe đạp bao gồm yên, bánh, bàn đạp,..). Khi lấy data từ database sẽ cố gắn ánh xạ các trường từ database sang model này. View khi render cũng dựa trên các trường trên model để render
3. **View**: View là file **\*.jsp** tại đây nhận data là model từ controller trả về rồi in ra.
4. **Service**: Chứa logic nghiệp vụ của ứng dụng. VD: lấy data từ đâu, như thế nào, xử lí data ở dạng các mảng, object, vv.


### Cấu trúc code (chính)
- Source chính chứa logic ứng dụng nằm ở **src/main/java** bao gồm 4 package như sau
    * music.controller: Đây là thành phần controller như đã nói ở trên được định nghĩa cho user thông thường
    * music.controller.admin: Cũng tương tự nhưng dành cho admin
    * music.dao: Đối tượng truy cập dữ liệu, package đóng vai trò lấy dứ liệu từ MYSQL ra và thực hiện các thao tác với nó. Bao gồm cả Interface và Implemment của nó. 
    * music.model: Định nghĩa các model theo mô hình MVC như đã nói ở trên
    * music.service: Định nghĩa các service như đã nói ở trên. Package này cũng bao gồm Interface và Implemment của một service
- Source chứa resouce (bao gồm file html, jsp, css, js) nằm ở **src/main/webapp/WEB-INF/** bao gồm 2 thư mục chính là
    * views: Chứa các view jsp được định nghĩa
    * resources: chứa các file có định dạnh khác (js, css, image, ..) 
### Ví dụ
```java
@RequestMapping("/product")
public class ProductController {
@RequestMapping("/productList") // là endpoint của url vd: http://localhost/productList
	public String getProductByCategory(@RequestParam("searchCondition") String searchCondition,
				Model model) {
		List<Product> products = productService.getProductList();
		model.addAttribute("products", products); // dùng trong data binding để view render
		model.addAttribute("searchCondition", searchCondition); // dùng trong data binding để view render
		
		return "productList"; // return tên view đã được định nghĩa trước
	}
}
```
### Module chọn để làm

1. Tính tổng giá trong giỏ hàng **CustomerOrderServiceImpl.java*
```java
public double getCustomerOrderGrandTotal(int cartId) {
		
		double grandTotal=0;
		
		Cart cart = cartService.getCartById(cartId);
		List<CartItem> cartItems = cart.getCartItems();
		
		for(CartItem item: cartItems) {
			grandTotal+=item.getTotalPrice();
		}
		
		
		return grandTotal;
	}
```
2. Kiểm tra user có tồn tại trong database để thêm mới user
```java
@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerCustomerPost(@Valid @ModelAttribute("customer") Customer customer,
			 BindingResult result ,Model model) {
		
		if(result.hasErrors()) {
			return "registerCustomer";
		}
		
		List<Customer> customerList = customerService.getAllCustomer();
		
		for (int i=0; i< customerList.size(); i++) {
            if(customer.getCustomerEmail().equals(customerList.get(i).getCustomerEmail())) {
                model.addAttribute("emailMsg", "Email already exists");

                return "registerCustomer";
            }

            if(customer.getUsername().equals(customerList.get(i).getUsername())) {
                model.addAttribute("usernameMsg", "Username already exists");

                return "registerCustomer";
            }
        }
		
		customer.setEnabled(true);
		customerService.addCustomer(customer);
		
		return "registerCustomerSuccess";
		
	}
```



