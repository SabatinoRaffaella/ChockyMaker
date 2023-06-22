<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <%Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
    if ((isAdmin == null) || (!isAdmin)){	
        response.sendRedirect(request.getContextPath() + "/login.jsp"); 
        return;
    }
    %>        
        <title>Add Product Form</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="adminprodControl.css"> 
            <link rel="stylesheet" href="../style.css">
            <script>
            function validate(){
		if(!validateName() || !validateDesc() || !validateBrand()) return false;
           }
            function validateName(){
                let n= document.forms["products"]["name"].value;
                var pattern=/^[A-Za-z]+$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="Product name must have alphabet characters only";
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
            function validateBrand(){
		let n= document.forms["products"]["brand"].value;
		var pattern=/^[A-Za-z]+$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="Product brand must have alphabet characters only";
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
            function validateDesc(){
		let n= document.forms["products"]["description"].value;
		var pattern= /^[A-Za-z0-9 _]*$/;
		//var pattern= /^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _] *$/;
		if(!n.match(pattern)){
                    document.getElementById("error").innerHTML="Product description must be a sequence of characters <br> and numbers separeted by whitespace without special characters";
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
     <jsp:include page="navibar.jsp"  flush="true"/>     
     <!-- Prima effetueremò la validazione chiamando uno script che una volta superati tutti i controlli
         restituirà true e permetterà al form di essere inviato al database !-->
        <div class="section-p1"><form name="products" method="post" action="../AddDataDatabase">
            <ol>
              <li><p>  Enter brand of the product: </p>
                   <input type="text" name="brand" onchange="validateBrand()" required>
              </li>
              <li><p>  Enter the name of the product: </p>  
                    <input type="text" name="name" onchange="validateName()" required>
              </li>          
              <li><p> Enter a short description of the product to be listed: </p>
                    <input type="text" name="description" onchange="validateDesc()" required>
              </li>			  
              <li><p>  Enter the price of the product to be listed: </p>
                    <input type="number" name="price" required>
              </li>			  
              <li><p>  Enter the amount left in stock of product to be listed: </p>
                    <input type="number" name="quantity" required>
              </li>
                <li><p> Enter the amount in gr of product to be listed </p>
                  <input type="number" name="amount" required>
               </li>			   
        	<li><p>  Enter a photo of the product to be listed: </p>
                <input type="file" name="img" accept="image/*" required>
              </li>              
              <li><input value="Submit" type="submit" name="submit" onclick="return validate()"></li>	      
            </ol>
            <div class="errormsg">
	      <p id="error"></p>
	    </div>   
        </form></div>    
    </body>
</html>
