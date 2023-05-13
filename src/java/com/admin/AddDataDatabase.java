package com.admin;
import java.io.IOException;
import Controller.DriverManagerConnectionPool;
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
            String brand = request.getParameter("brand");
            String name = request.getParameter("name");
            String desc = request.getParameter("description");
            
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            Double p = Double.valueOf(price);
            int q = Integer.parseInt(quantity);
                           
            String insertSQL = "INSERT INTO " + "Prodotto"
		+ " (Name, description, brand, price, quantity) VALUES (?, ?, ?, ?, ?)";
		try {    
                    ps = cn.prepareStatement(insertSQL);
		//	ps.setInt(1,id);			
                        ps.setString(1,name);
			ps.setString(2, desc);
                        ps.setString(3, brand);
			ps.setDouble(4,p);	
                        ps.setInt(5,q);						
			/*
                        preparedStatement.setInt(4, product.getQuantity());			
			preparedStatement.setDouble(5, product.getPrice());
                        */        
			ps.executeUpdate();
			cn.commit();   
		       
                        ps.close(); 
                        cn.close();
                   
            }catch(Exception e){
                    response.sendError(1, "Error during connection closing");
                    response.addHeader("er", "Error during connection closing");
            }
        }catch(Exception e){
               response.sendError(2,"Error during query insertion");
               response.addHeader("er", "Error during query insertion");			   
        }
      //  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProductView.jsp");
      //  dispatcher.forward(request, response);
    }    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
