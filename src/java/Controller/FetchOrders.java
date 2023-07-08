package Controller;

import Model.Listed;
import Model.Order;
import Model.Order_Details;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "FetchOrders", urlPatterns = {"/FetchOrders"})
public class FetchOrders extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FetchOrders</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FetchOrders at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DriverManagerConnectionPool mg= new DriverManagerConnectionPool();    
        ArrayList<Order> orders= new ArrayList<>();
        User u = (User)request.getSession().getAttribute("user");
        if(u==null){
            request.setAttribute("errors", "you need to be logged-in");
            response.sendRedirect("login.jsp");
            return;
        } 
        try{
            int order_id=0;
            int client_id= u.getId();
            LocalDate shipp_date=null;
            LocalDate order_date=null;
            String deliv_address="";   
            int order_am = 0;
            Listed l = (Listed)request.getSession().getAttribute("listed");
                if(l==null){
                    request.getSession().setAttribute("comeback", "/FetchOrders");
                    response.sendRedirect("FetchProductCSide");
                    return;
                }
            Connection cn = mg.getConnection();         
            PreparedStatement ps = cn.prepareStatement("select * from Ordine where id_cliente=?");
            ps.setInt(1, client_id);
            ResultSet rs = ps.executeQuery();  
            while(rs.next()){
                order_id = rs.getInt("id_ordine");
                shipp_date = rs.getObject("data_spedizione",LocalDate.class);               
                order_date = rs.getObject("data_ordine",LocalDate.class);
                deliv_address = rs.getString("indirizzo_consegna");
                Order d = new Order(order_id,u,shipp_date,order_date,deliv_address);
                orders.add(d);
                order_am++;
            }    
            int i=0;
            while(i<order_am){
                int id_ord = orders.get(i).getId_order();
                PreparedStatement pt;
                pt = cn.prepareStatement("select * from Dettagli_Ordine where id_ordine=?");
                pt.setInt(1, id_ord);
                rs = pt.executeQuery();
                ArrayList<Order_Details> dt= new ArrayList<>();
                int prod_id=0;
                int quantity=0;
                double sub_total=0;
                String pymnt_mth="";        
                while(rs.next()){
                    prod_id = rs.getInt("id_prodotto");
                    quantity = rs.getInt("quantità");
                    sub_total = rs.getDouble("sub_totale");
                    pymnt_mth = rs.getString("metodo_pagamento");
                    Order_Details d = new Order_Details(l.fetchByPrId(prod_id),quantity,sub_total,pymnt_mth);
                    dt.add(d);
                }
                orders.get(i).addOrderdt(dt);
                i++;
            }             
            request.getSession().setAttribute("orders", orders);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Orders"); 
            dispatcher.forward(request,response);
        }catch(SQLException sql){
            request.getSession().setAttribute("errormsg", sql.getMessage());
            response.sendRedirect("error/error.jsp");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
