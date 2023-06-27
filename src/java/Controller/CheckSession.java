package Controller;
import Model.Cart;
import Model.Listed;
import Model.Product;
import Model.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "CheckSession", urlPatterns = {"/CheckSession"})
//Servlet che si occupa di recuperare il nome di un prodotto da un campo di testo
//nascosto contenente il nome e controlla se esso è presente nel database
// prima di aggiungerlo alla sessione.
public class CheckSession extends HttpServlet {
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
        Cart usercart;
        if(request.getSession().getAttribute("userID")==null){    
            request.getSession().setAttribute("userID", 0); 
            usercart= new Cart(0);
        }
            /*Controllo se la sessione è nuova e modifico il valore nome del cookie in user altrimenti 
            Recupero quelli attivi al cui interno sarà contenuto il nome dell'utente una volta effettuato
            L'accesso
            */
        else{
           User u = (User)request.getSession().getAttribute("user");
           if(u!=null) request.getSession().setAttribute("userID", u.getUsername());
           usercart = (Cart)request.getSession().getAttribute("usercart");        
        }
        try{
           String action = request.getParameter("action");	   
            int id = Integer.parseInt(request.getParameter("id"))-1;                              
            Listed list = (Listed)request.getSession().getAttribute("listed");
            Product selected = (list.fetchById(id));
            if(action.matches("addC")){               
                if(usercart.checkIfPresent(selected)) usercart.fetchById(id).setAddedToCart(1);
                else usercart.addProduct(selected);
                request.getSession().setAttribute("usercart", usercart);
                request.getSession().setAttribute("msg","the element was added succesfully to the cart");                
                    //response.getWriter().print(request.getSession().getAttribute("userID"));
                    //response.getWriter().print(usercart.getProducts().toString());       
            }
            if(action.matches("delete")){
                if(!usercart.checkIfPresent(selected)) request.getSession().setAttribute("msg","the selected item isn't int the cart");
                else{ 
                    usercart.deleteProduct(selected);
                    request.getSession().setAttribute("msg","the selected item was removed from the cart");
                }
            }     
            response.sendRedirect("Shop");
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Shop"); 
            //dispatcher.forward(request,response);
        }
        catch(Exception e){
            request.setAttribute("errormsg", e.getMessage());
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