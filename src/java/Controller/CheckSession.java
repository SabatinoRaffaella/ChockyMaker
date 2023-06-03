package Controller;
import Model.Cart;
import Model.Listed;
import java.io.IOException;
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
           usercart = (Cart)request.getSession().getAttribute("usercart");
          // request.getSession().setAttribute("userID", );
        }
        try{
           String action = request.getParameter("action");	   
            int id = Integer.parseInt(request.getParameter("id"))-1;                              
            if(action.matches("addC")){
                Listed list = (Listed)request.getSession().getAttribute("listed");
                usercart.addProduct(list.fetchById(id));
                request.getSession().setAttribute("usercart", usercart);
                    //response.addCookie(cok);
                    response.getWriter().print(usercart.getProducts().toString());
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