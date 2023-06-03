<%-- 
    Document   : register
    Created on : 1-giu-2023, 18.05.54
    Author     : raffy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register here</title>
        <link rel="stylesheet" href="admin/adminprodControl.css">        
        <script src="https://kit.fontawesome.com/207052c3a9.js" integrity="sha512-BgwIN3PpXLkbg6HyWOm0LO0m1sBZr6gEHLStmyYQ+3WtPcbEJkhC5lH1iISIYI0pWi+L6snpMjPQ99mrWPagew==" crossorigin="anonymous"></script>                
        <script>
            function validate(){
		if(!validateEmail() || !validateName() || !validateSurname() || !validateAddress() || !validatePhoneNumber()) return false;
            }
            function validateEmail(){
                let n= document.forms["client"]["email"].value;
                var pattern=/^\S+@\S+\.\S+$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="Email must be in the form ____@____.___";
                    error.classList.remove("valid");
                    error.classList.add("invalid");				
                return false;
                }
                if(n.match(""))return true;
                else{
                    document.getElementById("error").innerHTML="OK";
                    error.classList.remove("invalid")									
                    error.classList.add("valid");	
		return true;					
		}		
            }
            function validateName(){
		let n= document.forms["client"]["name"].value;
		var pattern=/^[A-Za-z]+$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="Name must have alphabet characters only";
                    error.classList.remove("valid");
                    error.classList.add("invalid");				
                return false;
		}
		else{
                    document.getElementById("error").innerHTML="OK";	
                    error.classList.remove("invalid")									
                    error.classList.add("valid");	
                return true;					
		}		
            }
            function validateSurname(){
		let n= document.forms["client"]["surname"].value;
		var pattern= /^[A-Za-z]+$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="must have alphabet characters only";
                    error.classList.remove("valid");
                    error.classList.add("invalid");
		return false;
		}
		else{
                    document.getElementById("error").innerHTML="OK";	
                    error.classList.remove("invalid");									
                    error.classList.add("valid");	
		return true;		
		}		
            }
            function validateAddress(){
		let n= document.forms["client"]["address"].value;
		var pattern= /^[A-Za-z ,]*[0-9]{0,3}-[0-9]{5} \w*[(A-Z)]{4}$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="Must be like [Piazza Bartolo Longo,36-80045 Pompei(NA)]";
                    error.classList.remove("valid");
                    error.classList.add("invalid");
		return false;
		}
		else{
                    document.getElementById("error").innerHTML="OK";	
                    error.classList.remove("invalid");									
                    error.classList.add("valid");	
		return true;		
		}		
            }
            function validatePhoneNumber(){
                let n= document.forms["client"]["phoneNumber"].value;
		var pattern= /^(\((00|\+)39\)|(00|\+)39)?(38[890]|34[7-90]|36[680]|33[3-90]|32[89])\d{7}$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="Must be in the form +39 --- --- ----(numeri tutti attaccati)";
                    error.classList.remove("valid");
                    error.classList.add("invalid");
		return false;
		}
		else{
                    document.getElementById("error").innerHTML="OK";	
                    error.classList.remove("invalid");									
                    error.classList.add("valid");	
		return true;		
		}		
            }
            
            
        </script>
    </head>
    <body>
	 <jsp:include page="jsptofetch/header.jsp"  flush="true"/>     		
	<!-- Prima effetueremò la validazione chiamando uno script che una volta superati tutti i controlli
         restituirà true e permetterà al form di essere inviato al database !-->
        <div class="section-p1"><form name="client" method="post" action="Register">
            <ol>
              <li><p>  Enter your e-mail: </p>
                   <input type="text" name="email" onchange="validateEmail()">
              </li>
              <li><p>  Enter your name: </p>  
                    <input type="text" name="name" onchange="validateName()" required>
              </li>          
              <li><p> Enter you surname: </p>
                    <input type="text" name="surname" onchange="validateSurname()" required>
              </li>			  
              <li><p>  Enter your address: </p>
                    <input type="text" name="address" onchange="validateAddress()"required>
              </li>			  
              <li><p>  Enter your phone number: </p>
                    <input type="text" name="phoneNumber" onchange="validatePhoneNumber()" required>
              </li>
              <li><input value="Submit" type="submit" name="submit" onclick="return validate()"></li>	      
            </ol>
            <div class="errormsg">
	      <p id="error"></p>
	    </div>   
        </form></div>      
         
        <jsp:include page="jsptofetch/newsletter.jsp" flush="/true"/>
        <jsp:include page="jsptofetch/footer.jsp"  flush="true"/> 
    </body>
</html>
