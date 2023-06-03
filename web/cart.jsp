<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>ChockyMaker</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/207052c3a9.js" integrity="sha512-BgwIN3PpXLkbg6HyWOm0LO0m1sBZr6gEHLStmyYQ+3WtPcbEJkhC5lH1iISIYI0pWi+L6snpMjPQ99mrWPagew==" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="style.css">  
        <link rel="stylesheet" href="table.css">	 
    </head>
    <body>
        <jsp:include page="jsptofetch/header.jsp"  flush="true"/>
        <section id="cart" class="section-p1">
            <table>
                <th>
                    <tr>
                        <td>Remove</td>
                        <td>Image</td>
                        <td>Product</td>
                        <td>Price</td>
                        <td>Quantity</td>
                        <td>Subtotal</td>
                    </tr>
		</th>
		<tbody>
                    <tr>				
                        <td><a href="#"><i class="far fa-times-circle"></i></a></td>
                    </tr>
                </tbody>
            </table>
	</section>
        <jsp:include page="jsptofetch/footer.jsp" flush="/true"/>		
    </body>
</html>
