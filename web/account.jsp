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
        <% 
            String isLoggedIn = (String)request.getSession().getAttribute("isloggedIn");
            User u = (User)request.getSession().getAttribute("user"); 
            if(isLoggedIn==null || isLoggedIn.matches("no")) request.getSession().setAttribute("msg", "You need to be logged-in in order to view this page!"); 
           if(isLoggedIn==null || u==null || isLoggedIn.matches("no"))  response.sendRedirect("login.jsp");          
        %>
        <jsp:include page="jsptofetch/header.jsp"  flush="true"/>   
        <%if(u!=null){%>
        <h2>Welcome, <%=u.getUsername()%></h2>
        <h2><a href="mailbox.jsp"><i class="fa-sharp fa-solid fa-truck-fast trucky"></i></a></h2>
        <h4>This is your personal page where you can definitely(not) modify stuff</h4>
        <table id="showpr" class="section-p1">
            <caption>User info</caption>
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
       <%}%>     
        <p><a href="<%=request.getContextPath()%>/Logout">Logout</p>
    </body>
</html>
