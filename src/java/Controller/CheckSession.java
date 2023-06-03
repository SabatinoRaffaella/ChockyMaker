package Controller;
import Model.Cart;
import Model.Listed;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "CheckSession", urlPatterns = {"/CheckSession"})
//Servlet che si occupa di recuperare il nome di un prodotto da un campo di testo
//nascosto contenente il nome e controlla se esso è presente nel database
// prima di aggiungerlo alla sessione.
public class CheckSession extends HttpServlet {
    DriverManagerConnectionPool mg= new DriverManagerConnectionPool(); 
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
           /* String user_name; 
            Cart usercart; 
            user_name = "user";
            Cookie cok = new Cookie("user","newuser");
            if(request.getSession().isNew()){               
                request.getSession().setAttribute("user", "something@gmail.com");
                cok.setValue("user");
                usercart = new Cart("something@gmail.com");
            }
            /*Controllo se la sessione è nuova e modifico il valore nome del cookie in user altrimenti 
            Recupero quelli attivi al cui interno sarà contenuto il nome dell'utente una volta effettuato
            L'accesso
            *//*
            else{
                Cookie[] coki = request.getCookies();
                for(Cookie cokie: coki){
                    if(cokie.getName().matches("user"))
                        user_name = cokie.getValue();                   
                }
                usercart = (Cart)request.getSession().getAttribute("usercart");
                cok.setValue(user_name);
                request.getSession().setAttribute("user", user_name);
            }*/
            Cart usercart= new Cart(1);
            Connection cn=null;   
            /*if(usercart==null){
                usercart = new Cart("something@gmail.com");
            }*/
            try{
                String action = request.getParameter("action");	   
                int id = Integer.parseInt(request.getParameter("id"));               
                if(action.matches("addC")){
                    try{                           
                    int id_cliente =  usercart.getId_cliente();
                    double price = Double.parseDouble(request.getParameter("price"));
                    cn = mg.getConnection();
                    PreparedStatement pt,ps;
                    ResultSet rs;
                    ps= cn.prepareStatement ("select * from Carrello");
                    rs = ps.executeQuery();
                    Boolean isProdPresent = false;
                    Boolean isClientPresent = false;
                    while(rs.next()){
                        if(rs.getInt("id_cliente")==id_cliente){
                            if(rs.getInt("id_prodotto")==id){
                                isProdPresent=true;      
                               }
                            isClientPresent=true;
                        }    
                        System.out.println(isProdPresent);			
                    }           
                    if(isProdPresent==true && isClientPresent==true){              
                        pt = cn.prepareStatement
                        ("update Carrello set sub_totale= sub_totale+?,quantità=quantità+1 where id_prodotto=? and id_cliente=?");                       
                        pt.setDouble(1, price);
                        pt.setInt(2, id);
                        pt.setInt(3, id_cliente);                
                        pt.executeUpdate();
                        cn.commit();
                        pt.close();
                    }
                    else{
                        pt = cn.prepareStatement
                        ("insert into Carrello (id_cliente,id_prodotto,quantità,sub_totale) values (?,?,?,?)");
                        pt.setInt(1, id_cliente);        
                        pt.setInt(2, id);
                        pt.setInt(3, 1);
                        pt.setDouble(4, price);
                        pt.executeUpdate(); 
                        cn.commit();                        
                        pt.close();
                    }		                   
                    }    
                    catch(SQLException s){
                        if(cn!=null){
                            try{
                                response.getWriter().print(s.getMessage());
                                cn.rollback();
                            }
                            catch(SQLException excep){
                                Logger.getLogger(CheckSession.class.getName()).log(Level.SEVERE, null, s);               
                            }
                        }
                    }
		finally {
                    if (cn != null) try { cn.close(); } catch (SQLException logOrIgnore) {};
		}                   
                    //Listed list = (Listed)request.getSession().getAttribute("listed");
                    //usercart.addProduct(list.fetchById(id));
                    //response.addCookie(cok);
                    //response.getWriter().print(usercart.fetchById(id).toString());
                }                
            }
            catch(Exception e){
                request.getSession().setAttribute("errormsg", e.getMessage());
                response.sendRedirect("error/error.jsp");               
            }				
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