package Controller;
import Model.Cart;
import Model.Listed;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            Cart usercart= new Cart("something@gmail.com");
            Connection cn=null;   
            /*if(usercart==null){
                usercart = new Cart("something@gmail.com");
            }*/
            try{
                String action = request.getParameter("action");	   
                int id = Integer.parseInt(request.getParameter("id"));               
                if(action.matches("addC")){
                    try{                           
                    String email =  usercart.getEmail_cliente();
                    double price = Double.parseDouble(request.getParameter("price"));
                    cn = mg.getConnection();
                    PreparedStatement pt;
                    PreparedStatement ps;
                    ResultSet rs;
                    ps = cn.prepareStatement("select * from carrello where email_cliente = ?");
                    ps.setString(1,"email");
                    rs = ps.executeQuery();
                    Boolean isProdPresent = false;
                    double sub_totale=0;
                    int quantità=0;
                    while(rs.next()){  
                        if(rs.getInt("id_prodotto")==id){
			isProdPresent=true;
			sub_totale=rs.getDouble("sub_totale");
			quantità=rs.getInt("quantità");
			}
                    }           
                    if(isProdPresent==true){
                        sub_totale = sub_totale + price;
                        quantità = quantità + 1;
                        pt = cn.prepareStatement
                        ("update carrello set quantità=?,sub_totale=? where id_prodotto=?");                       
                        pt.setInt(1, quantità);
                        pt.setDouble(2, sub_totale);
                        pt.setInt(3, id);
                        pt.executeUpdate();
                    }
                    else{
                        pt = cn.prepareStatement
                        ("insert into Carrello (id_prodotto,email_cliente,quantità,sub_totale) values (?,?,?,?)");
                        pt.setInt(1, id);
                        pt.setString(2, email);    
                        pt.setInt(3, 1);
                        pt.setDouble(4, price);
                        pt.executeUpdate(); 
                        }
                    pt.close();
                    cn.close();
                    mg.releaseConnection(cn);
                    }    
                    catch(SQLException s){
                        response.getWriter().print(s.getMessage());
                        mg.releaseConnection(cn);
                        
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