package Model;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "Shop", urlPatterns = {"/Shop"})
public class Shop extends HttpServlet {   
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
	}
    @Override   
     /*Restituisce la lista contenuta nella classe Listed dei prodotti 
    Contenuti all'interno del database*/
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{  
	Listed listed = (Listed)request.getSession().getAttribute("listed");
        if(listed==null || listed.getProducts().isEmpty()){
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FetchProductCSide"); 
          dispatcher.forward(request,response);
        } 
    }
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
