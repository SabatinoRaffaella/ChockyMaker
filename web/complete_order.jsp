<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@page import="Model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complete Order</title>
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous"></script> 
          <link rel="stylesheet" href="styles/orderStyle">
   </head>
    <body>  
     <% 
      String isLoggedIn = (String)request.getSession().getAttribute("isloggedIn");
      Cart cart = (Cart)request.getSession().getAttribute("usercart");      
        if(cart==null || cart.getProducts().isEmpty()){
            cart =null;
                request.getSession().setAttribute("msg", "Your cart is empty!");
        }
        if(isLoggedIn==null || isLoggedIn.matches("no")) request.getSession().setAttribute("msg", "You need to be logged-in in order to complete the order!");                      
        if(isLoggedIn==null || isLoggedIn.matches("no") || cart==null) response.sendRedirect("Shop");  
    %>
     <jsp:include page="jsptofetch/header.jsp"  flush="true"/>     
    <div id="Details">
        <p>Net cost:</p><br>
        <%if(cart!=null){%>
        <%=cart.getSubT()%><%}%>
    </div>
        <form name="order" action="Complete_Order" method="post">
            <select name="payment_options" required>
            <option value="contanti" select="selected">Contanti</option>
            <option value="carta">Carta di credito</option>
            </select>
            <%ZoneId z = ZoneId.of( "Europe/Paris");
              LocalDate today = LocalDate.now( z );%>
            <input type="hidden" name="order_date" value="
                   <%=today%>">
            <input type="submit" value="Order Now">
        </form>
    </body>
</html>
