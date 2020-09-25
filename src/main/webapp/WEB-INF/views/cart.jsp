<%@include file="/WEB-INF/views/template/header.jsp" %>
 
 
<div class="container-wrapper">
	<div class="container">
	<br><br><br>
	<section>
		<div class="container">
			<h1>Cart</h1>
			
			<p>All the selected products in your shopping cart</p>
		</div>
	</section>
	<section class="container">
		<div>
			<a class="btn btn-danger pull-left"><span class="glyphicon glyphicon-remove-sign"></span>Clear Cart</a>
		</div>
		<table class="table table-hover">
			<tr>
				<th>Product</th>
				<th>Unit Price</th>
				<th>Quantity</th>
				<th>Price</th>
				<th>Action</th>
			</tr>
			<tr>
				<td>productName</td>
				<td>ProductPrice</td>
				<td>Quantity</td>
				<td>totalPrice</td>
				<td>remove button</td>
			</tr>
			<tr>
				<th></th>
				<th></th>
				<th>Grand Total</th>
				<th>grandTotal</th>
				<th></th>
			</tr>		
		</table>
		<a href="<spring:url value="/productList" /> ">Continue Shopping</a>
	</section>
	
    

<%@include file="/WEB-INF/views/template/footer.jsp" %>