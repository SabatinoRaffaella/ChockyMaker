<%@page import="Model.Listed"%>
<%@page import="Model.Product"%>
<%@page import="com.admin.FetchData"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" import Model.Product,Model.Cart>
<script src="https://kit.fontawesome.com/207052c3a9.js" integrity="sha512-BgwIN3PpXLkbg6HyWOm0LO0m1sBZr6gEHLStmyYQ+3WtPcbEJkhC5lH1iISIYI0pWi+L6snpMjPQ99mrWPagew==" crossorigin="anonymous"></script>
<title>Admin Dashboard</title>
</head>
<%   // Check user credentials
    Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
if ((isAdmin == null) || (!isAdmin)){	
    response.sendRedirect(request.getContextPath() + "/login.jsp"); 
    return;
}
%>
<%  Listed products= (Listed)request.getSession().getAttribute("listed");
    %>
<body>
    <p><a href="<%=request.getContextPath()%>/Logout">Logout</a></p>
    <h1>Welcome in the amdinistration panel!</h1>
    <p>Here you can manage products within the database...</p>
    <jsp:include page="navibar.jsp"  flush="true"/> 
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

