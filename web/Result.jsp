<%-- 
    Document   : Result
    Created on : 4-giu-2023, 16.30.11
    Author     : raffy
--%>

<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="Model.MakeCaptcha"%>
<%@page import="Model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  
            String onep = (String) request.getSession().getAttribute("onepass");
            if(onep==null || onep=="") response.sendRedirect("register.jsp");
            else{
                MakeCaptcha mk = new MakeCaptcha(onep);            
                BufferedImage rend = mk.renderImage(); 
                ByteArrayOutputStream baos = new ByteArrayOutputStream();           
                if(rend==null)  response.sendRedirect("register.jsp");   
                else {
                    ImageIO.write(rend, "png", baos);
                    baos.flush();
                    byte[] imageInByteArray = baos.toByteArray();
                    baos.close();
                    String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);                   
        %>  
        <link rel="stylesheet" href="style.css">
        <jsp:include page="jsptofetch/header.jsp"  flush="true"/>    
        <h2>To complete registration you must lastly input the captcha value</h2>
        <h2>displayed below into the field</h2>
        <img src="data:image/jpg;base64, <%=b64%>" alt="otp not found">
        <form id="comp" name="Completion" autocomplete="off"  action="FilterPassword" method="POST">
            <input type="text" name="OTP" placeholder="Insert here OTP" required>
            <input type="text" name="username" placeholder="Insert here username" required>
            <input type="text" name="Password" placeholder="Insert here Password" required>
            <input type="submit">  
        </form>
        <%}
        }%>
    </body>
</html>
