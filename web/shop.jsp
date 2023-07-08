<%@page import="Model.Cart"%>
<%@page import="Model.Product"%>
<%@page import="Model.Listed"%>
<%@page import="Model.Shop"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Shop page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="table.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" import Model.Product,Model.Cart>
	<script src="https://kit.fontawesome.com/207052c3a9.js" integrity="sha512-BgwIN3PpXLkbg6HyWOm0LO0m1sBZr6gEHLStmyYQ+3WtPcbEJkhC5lH1iISIYI0pWi+L6snpMjPQ99mrWPagew==" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous"></script> 
</head>
    <body>
        <jsp:include page="jsptofetch/header.jsp"  flush="true"/>
	<section id="page-header" >
        <h2>#WeLikeChocolate</h2>
        <p>Save up money to buy more chocky with some coupons and 70% off our products</p>
       </section>
       <div id="msg">
           <% String msg= (String)request.getSession().getAttribute("msg");
                if(msg==null) msg=""; 
            %>  
            <%= msg %>
       </div>           
    <%  Listed products= (Listed)request.getSession().getAttribute("listed");
    %>        
<h2>Details</h2>
<section id="showpr" class="section-p1">	
    <table> <caption>List of products available</caption> <th>Product</th>
	<%
	  if (products != null) {
          for(Product p: products.getProducts()){
	%>
        <tr><td><%=p.getBrand()%> <%=p.getName()%> <%=p.getAmount()%>gr.</td></tr>
        <tr><td><img alt="alt" src="img/prodotti/<%=p.getImg()%>"/></td></tr>
        <tr> 
            <td><%=p.getDescription()%>
            Left in stock: <%=p.getQuantity()%>
        </td>
        </tr>
        <tr>
        <td><%=p.getPrice()%>$</td>       
        
        <td><a href="CheckSession?action=delete&id=<%=p.getId()%>">Delete</a></td>
        <td><a href="CheckSession?action=read&id=<%=p.getId()%>">Details</a></td>
        <td><a href="CheckSession?action=addC&id=<%=p.getId()%>">Add to cart</a></td>
        </tr>    	
	<%            
            }
        }
	%>
        </table>
	</section>
     <jsp:include page="jsptofetch/newsletter.jsp" flush="/true"/>
     
     <jsp:include page="jsptofetch/footer.jsp"  flush="true"/>      
   </body>
</html>
	
