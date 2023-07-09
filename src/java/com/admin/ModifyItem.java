package com.admin;

import Controller.DriverManagerConnectionPool;
import Model.Listed;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "ModifyItem", urlPatterns = {"/ModifyItem"})
public class ModifyItem extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RemoveItem</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RemoveItem at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         DriverManagerConnectionPool mg= new DriverManagerConnectionPool();
          try{
        PreparedStatement ps;
        Connection cn= mg.getConnection();
            int id_pr = (Integer.parseInt(request.getParameter("id")));
            if(id_pr<=0){
             request.getSession().setAttribute("erromsg", "negative ids are not allowed");
             response.sendRedirect("admin/ViewProduct.jsp");
             return;
            }
            Listed lis = (Listed)request.getSession().getAttribute("listed");               
            String action = (String) request.getParameter("action");
            ps = cn.prepareStatement("select * from prodotto");
            if(action.matches("delete")){
                String removeSQL = "update Prodotto set deleted=? where Prodotto.id = ?";
                ps = cn.prepareStatement(removeSQL); 
                ps.setBoolean(1, true);
                ps.setInt(2,id_pr);
                lis.fetchByPrId(id_pr).setQuantity(0);
                lis.fetchByPrId(id_pr).setDeleted(true);               
            }
            else if(action.matches("mod_price")){
                Double d = Double.valueOf(request.getParameter("price"));
                String upSQL= "update Prodotto set price=? where Prodotto.id = ?";
                ps = cn.prepareStatement(upSQL); 
                ps.setDouble(1, d);
                ps.setInt(2,id_pr);
                lis.fetchByPrId(id_pr).setPrice(d);
            }
            try {         
                ps.executeUpdate();
                cn.commit();       
                response.sendRedirect("admin/ViewProduct.jsp");
            }catch(SQLException e){
               response.sendError(1, "Error during connection closing");
               response.addHeader("er", "Error during connection closing");
            }
	    finally{      
	      ps.close(); 
              cn.close();
              request.getSession().setAttribute("listed", lis);
	 }	
        }catch(Exception e){
            response.sendError(2,"Error during query insertion");
            response.addHeader("er", "Error during query insertion");
            request.setAttribute("errormsg", e.getMessage());
            response.getWriter().print("error: "+e.getMessage());
        }            
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
