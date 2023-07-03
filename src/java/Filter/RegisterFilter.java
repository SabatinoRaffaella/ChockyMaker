package Filter;
import Controller.DriverManagerConnectionPool;
import Model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    DriverManagerConnectionPool mg= new DriverManagerConnectionPool();  
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
            try{
                String errormsg="";
                Connection cn= mg.getConnection();
                PreparedStatement ps;
                ResultSet rs;
                ps = cn.prepareStatement("select count(email) from Utente where email=?");
                ps.setString(1, email);
                int count=0;
                rs= ps.executeQuery();
                while(rs.next()){
                    count = rs.getInt("count(email)");
                }
                if(count==1){
                    errormsg = "user already registered with inserted email";
                    request.getSession().setAttribute("error", errormsg);
                    response.sendRedirect("register.jsp");
                }
                else{
                    User newuser= new User(0,email,name,surname,address,phoneNumber);
                    String onepass = SecurePassword.generateRandomPassword();
                    request.getSession().setAttribute("onepass", onepass);
                    request.getSession().setAttribute("user", newuser);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Result.jsp");
                    dispatcher.forward(request,response);   
                }
            }
            catch(SQLException sql){
                String errormsg = "there was an unexpected error during registration \n"+sql.getMessage();
                request.getSession().setAttribute("error", errormsg);
                response.sendRedirect("register.jsp");
            }                 
    } 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
