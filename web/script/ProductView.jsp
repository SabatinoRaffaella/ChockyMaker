<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("OrderForm.html");	
		return;
	}
	Product product = (Product) request.getAttribute("products");
	
	Cart cart = (Cart) request.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,Model.Product,Model.Cart"%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="ProductStyle.css" rel="stylesheet" type="text/css">
	<title>Storage DS/BF</title>
</head>

<body>
	<h2>Products</h2>
	<a href="OrderForm.html">List</a>
	<table border="1">
		<tr>
			<th>Id <a href="product?sort=Id">Sort</a></th>
			<th>Name <a href="product?sort=name">Sort</a></th>
			<th>Description <a href="product?sort=description">Sort</a></th>
			<th>Action</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					Product bean = (Product) it.next();
		%>
		<tr>
			<td><%=bean.getId()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>						
			<td><%=bean.getBrand()%></td>
			<td><a href="product?driver=drivermanager&action=delete&id=<%=bean.getId()%>">Delete</a><br>
				<a href="product?driver=drivermanager&action=read&id=<%=bean.getId()%>">Details</a><br>
				<a href="product?driver=drivermanager&action=addC&id=<%=bean.getId()%>">Add to cart</a>
				</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<h2>Details</h2>
	<%
		if (product != null) {
	%>
	<table border="1">
		<tr>
			<th>Code</th>
			<th>Name</th>
			<th>Description</th>			
			<th>Brand</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		<tr>
			<td><%=product.getId()%></td>
			<td><%=product.getName()%></td>
			<td><%=product.getDescription()%></td>			
			<td><%=product.getBrand()%></td>
			<td><%=product.getPrice()%></td>
			<td><%=product.getQuantity()%></td>
		</tr>
	</table>
	<%
		}
	%>
	<h2>Insert</h2>
	<form action="product" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Name:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="enter name"><br> 
		
		<label for="description">Description:</label><br>
		<textarea name="description" maxlength="100" rows="3" required placeholder="enter description"></textarea><br>
		
		<label for="brand">Brand:</label><br> 
		<input name="brand" type="text" maxlength="20" required placeholder="enter brand"><br> 
		
		<label for="price">Price:</label><br> 
		<input name="price" type="number" min="0" value="0" required><br>

		<label for="quantity">Quantity:</label><br> 
		<input name="quantity" type="number" min="1" value="1" required><br>

		<input type="submit" value="Add"><input type="reset" value="Reset">
	</form>
	<% if(cart != null) { %>
		<h2>Cart</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Action</th>
		</tr>
		<% List<Product> prodcart = cart.getProducts(); 	
		   for(Product beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getName()%></td>
			<td><a href="product?action=deleteC&id=<%=beancart.getId()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>		
	<% } %>	
</body>
</html>