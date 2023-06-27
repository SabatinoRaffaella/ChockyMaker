<%-- 
    Document   : account
    Created on : 24-giu-2023, 17.45.30
    Author     : raffy
--%>

<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Settings</title>
        <link rel="stylesheet" href="style.css"> 
        <link rel="stylesheet" href="table.css">
    </head>
    <body>
        <%   String userID = (String)request.getSession().getAttribute("userID");
            if(userID==null ||  userID.equals(0)) userID=null;           
        %>
        <% User u = (User)request.getSession().getAttribute("user"); 
           if(u==null || userID==null)  response.sendRedirect("login.jsp");
           else{
        %>
        <h2>Welcome, <%=u.getUsername()%></h2>
        <h4>This is your personal page where you can definitely(not) modify stuff</h4>
        <table id="showpr" class="section-p1">
            <tr>
                <th>Nome</th>
                <th>Cognome</th>
                <th>Telefono</th>
                <th>Email</th>
                <th>Indirizzo</th>
            </tr>
            <tbody>
            <tr>
                <td><%=u.getName()%></td> 
                <td><%=u.getSurname()%></td>
                <td><%=u.getPhoneNumber()%></td> 
                <td><%=u.getEmail()%></td>
                <td><%=u.getAddress()%></td></tr>
            </tbody>
        </table>
        <% } %>
    </body>
</html>
