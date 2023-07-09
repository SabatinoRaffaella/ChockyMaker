<%@page import="Model.Listed"%>
<%@page import="Model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" import Model.Product,Model.Cart>
<script src="https://kit.fontawesome.com/207052c3a9.js" integrity="sha512-BgwIN3PpXLkbg6HyWOm0LO0m1sBZr6gEHLStmyYQ+3WtPcbEJkhC5lH1iISIYI0pWi+L6snpMjPQ99mrWPagew==" crossorigin="anonymous"></script>
<title>Admin Dashboard</title>
<link rel="stylesheet" href="../table.css">
<link rel="stylesheet" href="../styles/prod.css"> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous"></script> 
</head>
<script> 
    $(document).ready(function(){
         $('.form1').hide();         
         $('.modp').click(function(){
          if ($('.form1').hasClass("active")) {     
          $('.form1').removeClass("active");
          $('.form1').hide();          
        }
        else
        { 
          $('.form1').addClass("active");  
          $('.form1').show();                  
        }
    });       
    });
    </script>
    <script>
    function validate(){
        if(!validateId() || !validatePrice()) return false;
        
    }
    function validatePrice(){
        let n= document.forms["form1"]["price"].value;
        var pattern=/^(?:0\.[0-9]{1,2}|[1-9]{1}[0-9]*(\.[0-9]{1,2})?|0)$/;
	if(!n.match(pattern)){
            document.getElementById("error").innerHTML="didn't add price in a valid format (n.n) \n";
            error.classList.remove("valid");
            error.classList.add("invalid");				
            return false;
        }
        else{
           document.getElementById("error").innerHTML="OK";
           error.classList.remove("invalid");									
           error.classList.add("valid");	
           return true;					
        }	
    }
    function validateid(){
        let n= document.forms["form1"]["id"].value;
        var pattern=/^[1-9]+$/;
	if(!n.match(pattern)){
            document.getElementById("error").innerHTML="didn't add price in a valid format (n.n) \n";
            error.classList.remove("valid");
            error.classList.add("invalid");				
            return false;
        }
        else{
           document.getElementById("error").innerHTML="OK";
           error.classList.remove("invalid");									
           error.classList.add("valid");	
           return true;					
        }     
    }
    
</script>

<%
    Listed products= (Listed)request.getSession().getAttribute("listed");
    if(products==null) response.sendRedirect("../FetchProductCSide");
    String errormsg = (String) request.getSession().getAttribute("erromsg");
    if(errormsg==null) errormsg="";
    %>
<body>
    <p><a href="<%=request.getContextPath()%>/Logout">Logout</a></p>
    <h1>Welcome in the amdinistration panel!</h1>
    <p>Here you can manage products within the database...</p>
    <jsp:include page="navibar.jsp"  flush="true"/> 
    <h2>Details</h2>
    <section id="product1" class="section-p1">	
        <table border="1" id="showpr">           
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
            <th>Deleted</th>
    </tr>
	<%
	  if (products != null) {
          for(Product p: products.getProducts()){
	%>
	<tr>
        <td><a href="../ModifyItem?action=delete &id=<%=p.getId()%>"><i class="far fa-times-circle"></i></a></td>
        <td><%=p.getId()%></td>
	<td><%=p.getName()%></td>
        <td><%=p.getDescription()%></td>
        <td><%=p.getBrand()%></td>
        <td><button class="modp">
           <%=p.getPrice()%></button></td>  
	<td><%=p.getQuantity()%></td>
	<td><%=p.getAmount()%></td>
	<td><%=p.getImg()%></td>
        <td><%=p.isDeleted()%></td>
	</tr>
	<%
            }
        }
	%>
        </table>
         <div class="errormsg">
	      <p id="error"></p>
	    </div>
        <form name="form1" class="form1" action="../ModifyItem" method="post"> 
            <input type="hidden" name="action" value="mod_price">
            <input type="number" name="id" placeholder="id of product to modify price" onchange="validateid()">
            <input type="text" placeholder="new Price" name="price" onchange="validatePrice()">
            <input type="submit" value="submit">
        </form>
	</section>	
</body>
</html>

