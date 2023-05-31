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
	<script src="https://kit.fontawesome.com/207052c3a9.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
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
	<section id="header">	
	<a href="#"> <img src="img\logo\logo.PNG" class="logo" alt="alt"/></a>
        <div> 
            <ul id="navbar">
                <li><a href="index.html">Home</a></li> 
                <li><a class="active" href="shop.html">Shop</a></li> 
                <li><a href="blog.html">Blog</a></li> 
                <li><a href="about.html">About</a></li> 
                <li><a href="cotact.html">Contact</a></li> 
                <li><a href="cart.html" id="lg-bag"><img src="img\somecart2.png" height="60" width="60" alt="alt"/></a></li>
                    <a href="#" id="close"><i class="fa-solid fa-xmark"></i></a><script src="script/close.js"></script>
            </ul>                
        </div> 
        <div id="mobile" >
        <a href="cart.html"><img src="img\somecart2.png" height="60" width="60" alt="alt"/></a>    
        <i id="bar" class="fas fa-outdent"><script src="script/script.js"></script> </i>            
        </div>
    </section>	
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
    <footer class="section-p1">
    <div class="col">
	<img class="logo" src="img/logo/ChockyMaker.png" alt="alt">
	<h4>Contact</h4>
	<p><strong>Address:</strong> viaqualcosa n° 56, San Francisco</p>
	<p><strong>Phone:</strong> +01 2222 365/(91) 01 5432 6987</p>					
	<p><strong>Hours:</strong> 10:00 - 18:00, Mon - Sat </p>
	<div class="follow">
	<h4>Follow us</h4>
	<div class="icon">
	<i class="fab fa-facebook-f"></i>
	<i class="fab fa-twitter"></i>
        <i class="fab fa-instagram"></i>						
	<i class="fab fa-youtube"></i>
	</div>
	</div>	
	</div>
        <div class="col"> 
		<h4>About</h4>
		<a href="#">About us</a>
		<a href="#">Delivery Information</a>
		<a href="#">Privacy Policy</a>
		<a href="#">Terms & Conditions</a>				
		<a href="#">Contact Us</a>
	</div>	
	<div class="col"> 
                <h4>My Account</h4>
		<a href="#">Sign In</a>
		<a href="#">View Cart</a>
		<a href="#">My Wishlist</a>
                <a href="#">Track My Order</a>				
		<a href="#">Help</a>
	</div>
        <div class="col install">
            <h4> Install App </h4>
            <p>From App Store or Google Play</p>
            <div class="row">
        	<img src="img/logo/app store.png" alt="alt">
		<img src="img/logo/google play store.png" alt="alt">
		<p>Secure Payment Gateways </p>
		<img src="img/logo/Prepagate.png" alt="alt">
            </div>
	</div>
	<div class="copyright">
	<p>&copy 2023, Something etc - E-Commerce ChockyMaker</p>
	</div>
    </footer>
    </body>
</html>
	
