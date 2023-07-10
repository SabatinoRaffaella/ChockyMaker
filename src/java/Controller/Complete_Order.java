package Controller;

import Model.Cart;
import Model.Product;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "Complete_Order", urlPatterns = {"/Complete_Order"})
public class Complete_Order extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Complete_Order</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Complete_Order at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("complete_order.jsp");
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)           
            throws ServletException, IOException {
        DriverManagerConnectionPool mg= new DriverManagerConnectionPool();  
        User u = (User) request.getSession().getAttribute("user");
        Cart usercart =(Cart) request.getSession().getAttribute("usercart");
        String po = (String)request.getParameter("payment_options");
        String addressTo = u.getAddress();
        int order_id=0;
        try{
        Connection cn = mg.getConnection();
        cn.setAutoCommit(false);
        try{
            cn = mg.getConnection();       
                PreparedStatement ps;
                ps = cn.prepareStatement("insert into Ordine (id_cliente,data_spedizione,data_ordine,indirizzo_consegna) VALUES (?,?,?,?)");
                ps.setInt(1, u.getId());
                ps.setString(2, null);
                ps.setObject(3, request.getParameter("order_date"));
                ps.setObject(4, addressTo);
                ps.executeUpdate();
                cn.commit();
                
                PreparedStatement pt = cn.prepareStatement("select LAST_INSERT_ID() from Ordine");
                ResultSet rs = pt.executeQuery();
                while(rs.next()){
                    order_id = rs.getInt("LAST_INSERT_ID()");
                }     
                int i=0;
                Cart orderL = new Cart(u.getUsername());
                while(i<usercart.getProducts().size()){
                    pt = cn.prepareStatement("insert into Dettagli_Ordine (id_ordine,id_prodotto,quantità,sub_totale,metodo_pagamento) VALUES (?,?,?,?,?)");
                    Product p = usercart.fetchByOrder(i);
                    if(!p.isDeleted()){
                    int addedtoc = p.getAddedToCart();
                    int idp = usercart.fetchByOrder(i).getId();
                    pt.setInt(1, order_id);
                    pt.setInt(2,idp);
                    pt.setInt(3, addedtoc);
                    pt.setDouble(4, addedtoc * usercart.fetchByOrder(i).getPrice());
                    pt.setString(5, po);
                    pt.executeUpdate();
                    
                    orderL.addProduct(usercart.fetchByOrder(i));
                    }
                    i++;                  
                }              
            cn.commit();      
            
            if(usercart.emptyCart()){ 
            request.getSession().setAttribute("usercart", usercart);
            response.sendRedirect("Orders");
            }
            else{
                request.getSession().setAttribute("errormsg", "There was an error during the order: "+ "\n"+usercart.getProducts().size());
                response.sendRedirect("error/error.jsp");           
            }
        }
        catch(SQLException e){
            request.getSession().setAttribute("errormsg", "There was an error during the order: "+e.getMessage()+ "\n"+order_id+"   yy");
            response.sendRedirect("error/error.jsp");
            cn.rollback();
        }
        }catch(SQLException sql){
            request.getSession().setAttribute("errormsg", "There was an error during the order: "+sql.getMessage()+ "\n"+order_id+"   yy");
            response.sendRedirect("error/error.jsp");
           
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
