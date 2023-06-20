package Filter;
import Model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@WebServlet(name = "FilterPassword", urlPatterns = {"/FilterPassword"})
public class FilterPassword extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Filter filt = new Filter();
        String password = filt.Filter(request.getParameter("Password"));
        User u= (User)request.getSession().getAttribute("user");
        u.setPassword(password);
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Register");
          dispatcher.forward(request,response);              
    } 
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
