package Filter;
import Controller.DriverManagerConnectionPool;
import Model.User;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        /// Prima controlla che l'utente abbia inserito il giusto captcha 
        // se il captcha inserito è giusto, effettua l'escape dei caratteri dannosi 
        // e completa la registrazione.
        Filter filt = new Filter();
        String otp = filt.Filter(request.getParameter("OTP"));
        String onep= (String)request.getSession().getAttribute("onepass");
        if(!otp.matches(onep)){
            request.getSession().setAttribute("guessedOTP", "notguessed");
            request.getSession().setAttribute(onep, "");
            request.getSession().setAttribute("error","Captcha code was wrong!");
            response.sendRedirect("register.jsp");
            return;
        } 
        if(onep==null){
          request.getSession().setAttribute("error","error during finals steps of registration");
          response.sendRedirect("register.jsp");
          return;
        }
        
        DriverManagerConnectionPool mg= new DriverManagerConnectionPool();  
        String password = filt.Filter(request.getParameter("Password"));
        password = toHash(password);        
        String username = filt.Filter(request.getParameter("username"));        
        try{
            String errormsg="";
                Connection cn= mg.getConnection();
                PreparedStatement ps;
                ResultSet rs;
                ps = cn.prepareStatement("select count(nome_utente) from Utente where nome_utente=?");
                ps.setString(1, username);
                int count=0;
                rs= ps.executeQuery();
                while(rs.next()){
                    count = rs.getInt("count(nome_utente)");
                }
                if(count==1){
                    errormsg = "user already registered with the inserted username";
                    request.getSession().setAttribute("error", errormsg);
                    response.sendRedirect("register.jsp");
                }
                else{
                    User u= (User)request.getSession().getAttribute("user");
                    if(u==null){
                        request.getSession().setAttribute("error", "user ibnformation were empty when sent");
                        response.sendRedirect("register.jsp");
                    } 
                    u.setPassword(password);
                    u.setUsername(username);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Register");
                    dispatcher.forward(request,response);                 
                }
        }catch(SQLException sql){
            String errormsg = "there was an unexpected error during password filtering \n"+sql.getMessage();
            request.getSession().setAttribute("error", errormsg);
        }
        
    
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
