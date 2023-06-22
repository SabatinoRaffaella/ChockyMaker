package Controller;

import Filter.Filter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "AllowAcess", urlPatterns = {"/AllowAcess"})
public class AllowAcess extends HttpServlet {
    DriverManagerConnectionPool mg= new DriverManagerConnectionPool();  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	Filter filt = new Filter();		
        String password= filt.Filter(request.getParameter("password"));
        String username= filt.Filter(request.getParameter("username"));
        List<String> errors = new ArrayList<>();
            RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");			
            if(username == null || username.trim().isEmpty()) {
                errors.add("Il campo username non può essere vuoto!");
            }
            if(password == null || password.trim().isEmpty()) {
            	errors.add("Il campo password non può essere vuoto!");
			}
            if (!errors.isEmpty()) {
            	request.setAttribute("errors", errors);
            	dispatcherToLoginPage.forward(request, response);
            	return; // note the return statement here!!!
            }
            
            String hashPassword = toHash(password);
            String ruolo="";
            String pass="";
            try{
                PreparedStatement ps;
                ResultSet rs;
                Connection cn = mg.getConnection();
                ps = cn.prepareStatement("select ruolo,pass from utente where nome_utente=? and pass=?");
                ps.setString(1, username);
                ps.setString(2, hashPassword);
                rs= ps.executeQuery();
               
                while(rs.next()){
                    ruolo = rs.getString("ruolo");
                    pass =  rs.getString("pass");
                }
            }
            catch(SQLException se){}
            if(ruolo.matches("admin") && hashPassword.matches(pass)){ //admin
	        request.getSession().setAttribute("isAdmin", Boolean.TRUE); //inserisco il token nella sessione
                response.sendRedirect("FetchData");			
		} else if (ruolo.matches("cliente") && hashPassword.matches(pass)){ //user
			request.getSession().setAttribute("isAdmin", Boolean.FALSE); //inserisco il token nella sessione
			request.getSession().setAttribute("userID", username);
                        response.sendRedirect("Shop");
		} else {
			errors.add("Username o password non validi!");
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}	
     private static final long serialVersionUID = 1L;
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
