<%@page import="Model.Product"%>
<%@page import="Model.Order_Details"%>
<%@page import="Model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="table.css">
    </head>
    <body>
         <jsp:include page="jsptofetch/header.jsp"  flush="true"/>
        <section id="showpr" class="section-p1">
    <%  ArrayList<Order> orders= (ArrayList)request.getSession().getAttribute("orders");
     %>  
     <table> <caption>List of orders</caption> <th>Orders</th>
	<%
	  if (orders != null) {
          int i=0;
          for(Order od: orders){       
	%>
         <th>ID_ORDINE:</th>
        <tr><td><%=od.getId_order()%></td></tr>             
            <th>DETTAGLI ORDINE:</th>    
         <%i= od.getDt().size();
            ArrayList<Order_Details> odt = od.getDt();
         %>
         <% for(Order_Details t : odt){ %>
            <tr><td>Quantità Pr Acquistata: <%=t.getQt_pr()%></td></tr>
            <tr><td>Metodo di Pagamento: <%=t.getPaymn()%></td></tr>
            <tr><td>SubTotale: <%=t.getSub_p()%></td></tr>
            <tr>
            <%Product p=t.getP();%>
            <%if(p!=null){ %>
            <td><%=p.getName()%></td>
            <td><%=p.getBrand()%></td>
            <tr><td><img alt="alt" src="img/prodotti/<%=p.getImg()%>"/></td></tr>
            <td><%=p.getDescription()%></td>
            <td><%=p.getAmount()%></td>
            </tr>
           <% } %>           
       
        <%   }     
            }
        }
        %>
         </table>
        </section>
    </body>
</html>