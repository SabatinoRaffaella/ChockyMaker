<%@page import="Model.Order_Details"%>
<%@page import="Model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
    <%  ArrayList<Order> orders= (ArrayList)request.getSession().getAttribute("orders");
     %>  
     <table> <caption>List of orders</caption> <th>Orders</th>
	<%
	  if (orders != null) {
          int i=0;
          for(Order od: orders){       
	%>
         <br>
         <th>ID_ORDINE:</th>
        <tr><td><%=od.getId_order()%></td></tr>
        <tr>
        <td>
        </td>
        </tr>
        </table>
        <table>
            <th>DETTAGLI ORDINE:</th>    
         <%i= od.getDt().size();
            ArrayList<Order_Details> odt = od.getDt();
         %>
         <% for(Order_Details t : odt){ %>
            <tr><td><%=t.getP().toString()%></tr></td>
        <br>
            <tr><td>Quantità Pr Acquistata: <%=t.getQt_pr()%></td></tr>
            <br>
            <tr><td>Metodo di Pagamento: <%=t.getPaymn()%></td></tr>
            <br>
            <tr><td>SubTotale: <%=t.getSub_p()%></td></tr>
        </table>
        <%   }     
            }
        }
        %>
    </body>
</html>