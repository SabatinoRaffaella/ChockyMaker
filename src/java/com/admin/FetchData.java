package com.admin;
import Controller.DriverManagerConnectionPool;
import Model.Listed;
import Model.Product;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "FetchData", urlPatterns = {"/FetchData"})
public class FetchData extends HttpServlet {
   DriverManagerConnectionPool mg= new DriverManagerConnectionPool();    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

}
    @Override   
     /*Restituisce la lista contenuta nella classe Cart dei prodotti 
    Contenuti all'interno del database*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Listed listed = new Listed();
        Connection cn=null;   
        try {
            cn = mg.getConnection();
            PreparedStatement pt;
            ResultSet rs;
            pt = cn.prepareStatement("select * from Prodotto");
            rs = pt.executeQuery();
            while(rs.next()){
                Product p= new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setName(rs.getString("name"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getDouble("price"));
                p.setAmount(rs.getDouble("pr_amouunt"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImg(rs.getString("prod_image"));
                listed.addProduct(p);
            }           
        } 
        catch (SQLException ex) {
        Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(FetchData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getSession().setAttribute("listed", listed);
        response.sendRedirect("admin/ViewProduct.jsp");
    }
	//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/ViewProduct.jsp");
        //dispatcher.forward(request,response);     
        //non effettua redirect alla pagina desiderata
       // nel senso che non ti sposta verso quella pagina ma esegue il codice contenuto all'interno
        //all'interno della jsp 
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
