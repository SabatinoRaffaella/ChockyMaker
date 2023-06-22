<%-- 
    Document   : mailbox
    Created on : 20-giu-2023, 11.21.45
    Author     : raffy
--%>

<%@page import="Controller.Email.Email"%>
<%@page import="Controller.Email.EmailList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%  EmailList emails= (EmailList)request.getSession().getAttribute("emails");
     %>  
     <table> <caption>List of emails received</caption> <th>Emails</th>
	<%
	  if (emails != null) {
          for(Email em: emails.getEmails()){       
	%>
        <tr><td><%=em.getRecipient()%></td></tr>
        <tr><td><%=em.getSubject()%></td>       
	</tr>
        <tr> 
        <td>
            <%=em.getContent()%>
        </td>
	</tr>
	<%            
            }
        }
	%>
        </table>
	</section>
    
    </body>
</html>
