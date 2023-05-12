<%-- 
    Document   : connectDB
    Created on : 7-apr-2023, 16.25.38
    Author     : raffy
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
           <%
        String jacob;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/starmercedb";
            Connection con = DriverManager.getConnection(url, "root", "sabata");
        
            jacob="Connection ok";           
        } catch (ClassNotFoundException e) {
            jacob= ("DB driver not found \n");
        } catch (Exception e) {
            jacob= ("Connessione Fallita \n");
        }      
   %>
   The outcome was: <%=jacob%>
    </body>
</html>
