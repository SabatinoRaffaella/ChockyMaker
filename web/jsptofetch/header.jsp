<%-- 
    Document   : header
    Created on : 31-mag-2023, 18.56.01
    Author     : raffy
--%>

<%@page import="Model.Product"%>
<%@page import="Model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="en">
    <head><title>Header</title>
        <!-- Basta includere uno script nella sezione di file in cui 
        viene usato e basta importarlo nel file contenente la sezione usata 
        senza doverlo importare da altre parti-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous"></script> 
    <script src="https://kit.fontawesome.com/207052c3a9.js" integrity="sha512-BgwIN3PpXLkbg6HyWOm0LO0m1sBZr6gEHLStmyYQ+3WtPcbEJkhC5lH1iISIYI0pWi+L6snpMjPQ99mrWPagew=="" crossorigin="anonymous"></script>  
    </head>
    <script> 
     function hasClass(element, clsName) {
    return (' ' + element.className + ' ').indexOf(' ' + clsName + ' ') > -1;
    }
    $(document).ready(function(){
         $('#giggino').hide();         
         $('#lg-bag').click(function(){
          if (hasClass(document.getElementById('giggino'),"active")) {     
          document.getElementById('giggino').classList.remove("active");
          $('#giggino').hide();          
        }
        else
        { 
          document.getElementById('giggino').classList.add("active");  
          $('#giggino').show();                  
        }
    });       
    });
    </script>
       <link rel="stylesheet" href="style.css"> 
       <section id="header">
        <a href="#"> <img src="img\logo\logo.PNG" class="logo" alt="alt"/></a>
        <div>              
            <ul id="navbar">
                <li><a class="active" href="index.jsp">Home</a></li> 
                <li><a href="Shop">Shop</a></li> 
                <li><a href="login.jsp">Login</a></li> 
                <li><a href="register.jsp">Register</a></li> 
                <li><a href="account.jsp"><i class="fa-solid fa-user-gear" style="color: #CCCC66;"></i></a></li>
                <li><a id="lg-bag"><img src="img\somecart2.png" height="60" width="60" alt="alt"/></a>
                </li>
                <a href="#" id="close"><i class="fa-solid fa-xmark"></i></a><script src="script/close.js"></script>
            </ul> 
        </div> 
         <div id="giggino" > 
             <a href="cart.jsp">Checkout</a>
             <br>
                <%
                    if(request.getSession().getAttribute("usercart")==null);
                    else{
                    Cart carrello = (Cart)request.getSession().getAttribute("usercart");                                        
                    for(Product p: carrello.getProducts()){            
                %>
                <%=p.toString()%>
               <% }}%>
        </div>           
        <div id="mobile">
        <a href="cart.jsp"><img src="img\somecart2.png" height="60" width="60" alt="alt"/></a>    
        <i id="bar" class="fas fa-outdent"><script src="script/script.js"></script> </i>            
        </div>
        <br>        
        </section>                
</html>
