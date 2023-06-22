<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login form</title>
</head>
<body>
   <link rel="stylesheet" href="style.css">
    <jsp:include page="jsptofetch/header.jsp"  flush="true"/>     
<% 
List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>
<form action="AllowAcess" method="post"> 
<fieldset>
     <legend>Login Custom</legend>
     <label for="username">Username</label>
     <input id="username" type="text" name="username" placeholder="enter username">
     <br>   
     <label for="password">Password</label>
     <input id="password" type="password" name="password" placeholder="enter password">
     <br>
     <input type="submit" value="Login"/>
     <input type="reset" value="Reset"/>
</fieldset>
</form> 
 <jsp:include page="jsptofetch/footer.jsp" flush="/true"/>	
</body>
</html>


