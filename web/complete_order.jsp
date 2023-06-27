<%@page import="Model.Cart"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complete Order</title>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js" ></script>
    </head>
    <body>
     <% 
        String passw= null;  
        Cookie[] cks= request.getCookies();
        if(cks.length==0){
            request.getSession().setAttribute("msg", "You need to be logged-in in order to complete the order!");  
            passw=null;
        }
        for(Cookie ck: cks){
            if(ck==null){
            request.getSession().setAttribute("msg", "You need to be logged-in in order to complete the order!");  
            passw=null;      
            } 
            else passw = ck.getValue();
        }     
      Cart cart = (Cart)request.getSession().getAttribute("usercart");
        if(cart==null || cart.getProducts().isEmpty()){
                request.getSession().setAttribute("msg", "Your cart is empty!");
        }
            if(passw==null || cart==null) response.sendRedirect("Shop"); 
            /*
             String userID = (String) request.getSession().getAttribute("userID");           
            if(userID==null || userID.equals("default") || userID.isEmpty()){ 
                request.getSession().setAttribute("msg", "You need to be logged-in in order to complete the order!"+userID);
            }*/        
    %>
        <form name="order" action="Complete_Order" method="post">
            <select name="payment_options" id="pay" form="order">
            <option value="contanti">Contanti</option>
            <option value="carta">Carta di credito</option>
            </select>
            <input type="hidden" name="order_date" value="
                   <%SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    sdf.format(new Date());%>">
            <input type="submit" value="Order Now">
        </form>
    </body>
</html>
