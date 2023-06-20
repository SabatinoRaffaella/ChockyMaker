package com.admin;
import java.io.IOException;
import Controller.DriverManagerConnectionPool;
import Filter.Filter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "AddDataDatabase", urlPatterns = {"/AddDataDatabase"})
public class AddDataDatabase extends HttpServlet {    
    DriverManagerConnectionPool mg= new DriverManagerConnectionPool();
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
        try{
        PreparedStatement ps;
        Connection cn= mg.getConnection();
        Filter filt = new Filter();
            String brand = filt.Filter(request.getParameter("brand"));
            String name = filt.Filter(request.getParameter("name"));
            String desc = filt.Filter(request.getParameter("description"));            
            Double p = Double.valueOf( request.getParameter("price"));
            int q = Integer.parseInt (request.getParameter("quantity"));
            Double pr_amouunt = Double.valueOf(request.getParameter("pr_amouunt"));
            String imgpath= request.getParameter("img");            
            String insertSQL = "INSERT INTO " + "Prodotto"
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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/script/ProductView.jsp");
      //  dispatcher.forward(request, response);
    }    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
