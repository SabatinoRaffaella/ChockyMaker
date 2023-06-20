package Filter;
import Model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "RegisterFilter", urlPatterns = {"/RegisterFilter"})
public class RegisterFilter extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Filter filt = new Filter();
            String email = filt.Filter(request.getParameter("email"));
            String name = filt.Filter(request.getParameter("name"));
            String surname = filt.Filter(request.getParameter("surname"));            
            String address = filt.Filter(request.getParameter("address")); 
            String phoneNumber =  filt.Filter(request.getParameter("phoneNumber")); 
            User newuser= new User(email,name,surname,address,phoneNumber);
            String onepass = SecurePassword.generateRandomPassword();
            newuser.setPassword(onepass);
            request.getSession().setAttribute("user", newuser);
            
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/SendEmail");
        dispatcher.forward(request,response);              
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
