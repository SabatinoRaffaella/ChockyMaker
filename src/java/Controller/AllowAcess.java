package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "AllowAcess", urlPatterns = {"/AllowAcess"})
public class AllowAcess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password= request.getParameter("password");
        String username= request.getParameter("username");
        //PrintWriter out= response.getWriter();
        if(password.matches("admin") && username.matches("admin")){
          response.sendRedirect("connectDB.jsp");
            // out= response.getWriter();
         //  out.println("You guessed right, lucky you!!!");
        }
     //   else out.println("You guessed wrong password, you pour soul!!!");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
