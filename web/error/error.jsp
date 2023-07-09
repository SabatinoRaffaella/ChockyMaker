<%-- 
    Document   : error
    Created on : 27-mag-2023, 15.16.32
    Author     : raffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  String errormsg = (String) session.getAttribute("errormsg");
        
        %>
        <%= errormsg %>
    </body>
</html>
