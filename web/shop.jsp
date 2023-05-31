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
	<script src="https://kit.fontawesome.com/207052c3a9.js" integrity="sha384-oqVuAfXRKap7fdgcCY5uykM6+R9GqQ8K/uxy9rx7HNQlGYl1kPzQho1wx4JwY8wC" crossorigin="anonymous"></script>
        <script type="text/javascript" integrity="sha384-oqVuAfXRKap7fdgcCY5uykM6+R9GqQ8K/uxy9rx7HNQlGYl1kPzQho1wx4JwY8wC" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                $('#sendPr_name').click(function(){
                    $.ajax({
                        type: "post",
                        url: "CheckSession",
                        data: $('#pr_name').val(), 
                        success: function(){
                            document.getElementById("msg").innerHTML = $('#pr_name').val();
                        }
                    });
                });
                
            });
        </script>
        
</head>
    <body> 
	<jsp:include page="header.jsp"  flush="true"/>       
	    <section id="page-header" >
        <h2>#WeLikeChocolate</h2>
        <p>Save up money to buy more chocky with some coupons and 70% off our products</p>
       </section>
    <%  Listed products= (Listed)request.getAttribute("listed");
    %>        
<h2>Details</h2>
<section id="cart" class="section-p1">	
    <table> 
	<%
	  if (products != null) {
          for(Product p: products.getProducts()){       
	%>
        <tr><td><%=p.getBrand()%> <%=p.getName()%> <%=p.getAmount()%>gr.</td></tr>
        <tr><td><img src="img/prodotti/<%=p.getImg()%>"/></td>       
	</tr>
        <tr> 
        <td><%=p.getDescription()%>
            <br>Left in stock: <%=p.getQuantity()%>
        </td>
        <tr>
        <td><%=p.getPrice()%>$</td>
        <td><a href="CheckSession?action=delete&id=<%=p.getId()%>">Delete</a><br></td>
        <td><a href="CheckSession?action=read&id=<%=p.getId()%>">Details</a><br></td>
        <td><a href="CheckSession?action=addC&id=<%=p.getId()%>&price=<%=p.getPrice()%>">Add to cart</a></td>
        </tr>    
	</tr>
	<%            
            }
        }
	%>
        </table>
	</section>
        <div id="msg"> </div>
<section id="newsletter" class="section-p1 section-m1">
    <div class="newstext">
    <h4>Sign Up For Newsletter </h4>
    <p>Get E-mail updates about our latest shop and <span>special offers.</span></p>
    </div>
    <div class="form">
	<input type="email" placeholder="Your email address">
	<button>Sign Up</button>
    </div>	
    </section>
    <jsp:include page="footer.jsp"  flush="true"/>       
	</body>
</html>
	
