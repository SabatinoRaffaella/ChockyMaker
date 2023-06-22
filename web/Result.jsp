<%-- 
    Document   : Result
    Created on : 4-giu-2023, 16.30.11
    Author     : raffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/Result.css"/>
        <script>
            const newp = document.getElementById('newPass');
            const comp = document.getElementById('comp');
        if(comp){
            comp.addEventListener('click',()=>{
            newp.classList.add('active');
        });
        }   
        </script>
    </head>
    <body>
        <h2>We have sent to you a one-time use password</h2>
        <h2>Check it out to complete Registration</h2>
        <h2>You can access it from here <a href="mailbox.jsp">mailbox</a></h2>
        <form id="comp" name="Completion" autocomplete="off"  action="FilterOTP" method="POST">
            <input type="text" name="OTP" placeholder="Insert here OTP" required>
            <input type="submit" onclick="newPass.classList.add('active');">
        </form>
        <form id="newPass" name="newPass" action="FilterPassword" method="POST">
            <input type="text" name="username" placeholder="Insert here username" required>
            <input type="text" name="Password" placeholder="Insert here Password" required>
            <input type="submit">        
        </form>
    </body>
</html>
