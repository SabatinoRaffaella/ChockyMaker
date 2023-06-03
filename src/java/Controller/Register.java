package Controller;
import Filter.Filter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        PreparedStatement ps;
        Connection cn= mg.getConnection();
        Filter filt = new Filter();
            String email = filt.Filter(request.getParameter("email"));
            String name = filt.Filter(request.getParameter("name"));
            String desc = filt.Filter(request.getParameter("description"));            
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            Double p = Double.valueOf(price);
            int q = Integer.parseInt(quantity);
            Double pr_amouunt = Double.valueOf(request.getParameter("pr_amouunt"));
            String imgpath= request.getParameter("img");
            String insertSQL = "INSERT INTO " + "Utente"
		+ " (Name, description, brand, price, quantity,pr_amouunt, prod_image) VALUES (?, ?, ?, ?, ?, ?, ?)";	        
	  //ps.setInt(1,id);			       
            ps = cn.prepareStatement(insertSQL);
            try {
                ps.setString(1,name);
		ps.setString(2, desc);
		ps.setString(3, brand);
		ps.setDouble(4,p);	
		ps.setInt(5,q);	
		ps.setDouble(6,pr_amouunt);
		ps.setString(7,imgpath);				
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
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
