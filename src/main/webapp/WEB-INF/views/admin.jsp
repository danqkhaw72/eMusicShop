
<%@include file="/WEB-INF/views/template/header.jsp" %>


    


    

    <div class="container-wrapper">
      <div class="container">  
      <br><br><br> 
		  <div class="page-header">
		  	<h1>Administrator Page</h1>
		  	
		  	<p class="load"> This this the administrator page!</p>
		  </div>
		  
		  <c:if test="${pageContext.request.userPrincipal.name != null}" >
		  	<h2>
		  		Welcome: ${pageContext.request.userPrincipal.name} | <a href="<c:url 
		  		value="/j_spring_security_logout" />" >Logout</a>
		  	</h2>
		  </c:if>
		  
		  <h3>
		  	<a href="<c:url value="/admin/productInventory" />" >Product Inventory</a>
		  </h3>
		  
		  <p>Here you can view, check and notify the product inventory<p>
		  <br><br>
		  
		  <h3>
		  	<a href="<c:url value="/admin/customer" />" >Customer Managementy</a>
		  </h3>
		  
		  <p>Here you can view the customer information<p>
		  
		  
		  
<%@include file="/WEB-INF/views/template/footer.jsp" %>