<%-- 
    Document   : hello
    Created on : 7-apr-2023, 15.49.39
    Author     : raffy
--%>

<%@page import="java.util.Random"%>
<%@page import="java.util.Date"%>
<%@page import="Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
        import= "Model.Order"%>
  <jsp:useBean id= "order" class="Model.Order">
      <jsp:setProperty name="order" value= "" property="dateOrder"></jsp:setProperty>
   </jsp:useBean>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>    
        
        <h1>Hello World!</h1>
        <%  
            String desc,type,brand;
            desc = request.getParameter("description");
            type = request.getParameter("type");
            brand = request.getParameter("brand");
            Random n = new Random();           
            int n1= n.nextInt(100);;
            String s = "ab";
            s = s.concat("a"+n1);
            Product p = new Product(desc,type,brand,s); 
            order.addOrderedItem(p);
            Date d= new java.util.Date();        
            order.setDateOrder(d.toString());
            %>
       Hello, Here are the order information: <%= order %>     
    </body>
</html>
