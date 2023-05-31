<%-- 
    Document   : header
    Created on : 31-mag-2023, 18.56.01
    Author     : raffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="en">
    <head><title>Header</title></head>
       <link rel="stylesheet" href="style.css">      
       <section id="header">
        <a href="#"> <img src="img\logo\logo.PNG" class="logo" alt="alt"/></a>
        <div>              
            <ul id="navbar">
                <li><a class="active" href="index.html">Home</a></li> 
                <li><a href="Shop">Shop</a></li> 
                <li><a href="blog.html">Blog</a></li> 
                <li><a href="about.html">About</a></li> 
                <li><a href="contact.html">Contact</a></li> 
                <li><a href="cart.html" onclick="" id="lg-bag"><img src="img\somecart2.png" height="60" width="60" alt="alt"/></a></li>
                <a href="#" id="close"><i class="fa-solid fa-xmark"></i></a><script src="script/close.js"></script>
            </ul>                
        </div> 
        <div id="mobile">
        <a href="cart.html"><img src="img\somecart2.png" height="60" width="60" alt="alt"/></a>    
        <i id="bar" class="fas fa-outdent"><script src="script/script.js"></script> </i>            
        </div>
        </section>    
    </body>
</html>
