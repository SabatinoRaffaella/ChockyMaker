package Filter;
import Model.User;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        password = toHash(password);
        String username = filt.Filter(request.getParameter("username"));
        User u= (User)request.getSession().getAttribute("user");
        u.setPassword(password);
        u.setUsername(username);
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Register");
          dispatcher.forward(request,response);              
    } 
    private String toHash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString( 
                   (hash[i] & 0xFF) | 0x100 )
				   .toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return hashString;
    }    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
