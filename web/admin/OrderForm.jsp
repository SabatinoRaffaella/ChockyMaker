<%@page import="Model.Product"%>
<%@page import="Model.Cart"%>
<%@page import="com.admin.FetchData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" import Model.Product,Model.Cart>
<script src="https://kit.fontawesome.com/207052c3a9.js"integrity="sha384-oqVuAfXRKap7fdgcCY5uykM6+R9GqQ8K/uxy9rx7HNQlGYl1kPzQho1wx4JwY8wC" crossorigin="anonymous"></script>
<title>Admin Dashboard</title>
</head><!-- 
// Check user credentials
Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if ((isAdmin == null) || (!isAdmin)){	
    response.sendRedirect(request.getContextPath() + "/login.jsp"); 
    return;
}
%>
 -->
<%  Cart products= (Cart)request.getAttribute("cart");
    %>
<body>
<h1>Welcome in the amdinistration panel!</h1>
<p>
Here you can manage products within the database...
</p>
<p>
<a href="<%=request.getContextPath()%>/common/Logout">Logout</a>
</p>
<h2>Details</h2>
<section id="cart" class="section-p1">	
    <table border="1">           
    <tr>
       <th>Remove</th>
       <th>Id</th>
       <th>Name</th>
       <th>Description</th>
       <th>Brand</th>
       <th>Price</th>	
       <th>Quantity</th>
       <th>Amount</th>
       <th>Image</th>
    </tr>
	<%
	  if (products != null) {
          for(Product p: products.getProducts()){
	%>
	<tr>
        <td><i class="far fa-times-circle"></i></td>
        <td><%=p.getId()%></td>
	<td><%=p.getName()%></td>
        <td><%=p.getDescription()%></td>
        <td><%=p.getBrand()%></td>
	<td><%=p.getPrice()%></td>
	<td><%=p.getQuantity()%></td>
	<td><%=p.getAmount()%></td>
	<td><%=p.getImg()%></td>
	</tr>
	<%
            }
        }
	%>
        </table>
	</section>	
</body>
</html>

