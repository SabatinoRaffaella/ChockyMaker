package Filter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "FilterOTP", urlPatterns = {"/FilterOTP"})
public class FilterOTP extends HttpServlet {

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
        String password = filt.Filter(request.getParameter("OTP"));
        String onep= (String)request.getSession().getAttribute("onepass");
        if(password.matches(onep)){
            request.getSession().setAttribute("guessedOTP", "guessed");
            response.sendRedirect("Result.jsp");
        } 
        else{
            request.getSession().setAttribute("guessedOTP", "notguessed");
            request.getSession().setAttribute(onep, "");
            response.sendRedirect("error/error.jsp");
        } 
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
