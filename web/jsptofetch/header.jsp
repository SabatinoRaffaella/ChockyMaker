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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js" ></script>
    </head>
    <script>
     function hasClass(element, clsName) {
    return (' ' + element.className + ' ').indexOf(' ' + clsName + ' ') > -1;
    }
    $(document).ready(function(){
         $('#lpcart').hide();         
         $('#lg-bag').click(function(){
          if (hasClass(document.getElementById('lpcart'),"active")) {     
          document.getElementById('lpcart').classList.remove("active");
          $('#lpcart').hide();          
        }
        else
        { 
          document.getElementById('lpcart').classList.add("active");  
          $('#lpcart').show();                  
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
                <li><a href="blog.html">Blog</a></li> 
                <li><a href="login.jsp">Login</a></li> 
                <li><a href="register.jsp">Register</a></li>                
                <li><a id="lg-bag"><img src="img\somecart2.png" height="60" width="60" alt="alt"/></a>
                <div id="lpcart" >           
                <%
                    Cart carrello = (Cart)request.getSession().getAttribute("usercart");
                    for(Product p: carrello.getProducts()){            
                %>
                <%=p.toString()%>
               <% }%>
                </div>
                </li>
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
