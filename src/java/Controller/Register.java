package Controller;
import Model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}
    DriverManagerConnectionPool mg= new DriverManagerConnectionPool();
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
       
   try{
       
       User u= (User)request.getSession().getAttribute("user");
       response.getWriter().print(u.getAddress()+"  "+u.getPassword());
       PreparedStatement ps;
        Connection cn= mg.getConnection();
        String insertSQL = "INSERT INTO " + "Utente"
		+ " (email, nome_utente, indirizzo, pass, nome,cognome, telefono,ruolo) VALUES (?, ?, ?, ?, ?, ?, ?,?)";	        
	  //ps.setInt(1,id);			       
            ps = cn.prepareStatement(insertSQL);
            try {
                ps.setString(1,u.getEmail());
		ps.setString(2, u.getName());
		ps.setString(3, u.getAddress());
		ps.setString(4,u.getPassword());	
		ps.setString(5,u.getName());	
		ps.setString(6,u.getSurname());
		ps.setString(7,u.getPhoneNumber());               
		ps.setString(8,"cliente");
		ps.executeUpdate();
                cn.commit();   
            }catch(Exception e){
               response.sendError(1, "Error during connection closing");
               response.addHeader("er", "Error during connection closing");
            }
	    finally{
	      ps.close(); 
              cn.close();           
	 }	
        }catch(Exception e){
               response.sendError(2,"Error during query insertion");
               response.addHeader("er", "Error during query insertion");			   
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request,response); 
}
   @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
