package Controller.Email;
import Model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "SendEmail", urlPatterns = {"/SendEmail"})
public class SendEmail extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
        User usr = (User)request.getSession().getAttribute("user");
        String onepass = usr.getPassword();
        String recipient = usr.getEmail();
        String subject = "Complete Registration:";
        String content = "We are sending to you the one-time password to complete registration.\n"+onepass;
       
        String resultMessage = "";
        try {
            Email em = new Email(recipient,subject,content);
            EmailList l = new EmailList();
            l.addEmail(em);
            request.getSession().setAttribute("emails", l);
            //response.sendRedirect("Result.jsp");
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Result.jsp");
            //dispatcher.forward(request,response);   
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            response.sendRedirect("Result.jsp");
           
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
