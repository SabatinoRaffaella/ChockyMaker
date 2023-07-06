package Controller;

import Filter.Filter;
import Model.User;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "AllowAcess", urlPatterns = {"/AllowAcess"})
public class AllowAcess extends HttpServlet {
    DriverManagerConnectionPool mg= new DriverManagerConnectionPool();  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        User checku = (User) request.getSession().getAttribute("user");
        if(checku!=null ){
            request.setAttribute("errors", "already logged in"); 
            response.sendRedirect("login.jsp");
            return;
        }
	Filter filt = new Filter();		
        String password= filt.Filter(request.getParameter("password"));
        String username= filt.Filter(request.getParameter("username"));
        List<String> errors = new ArrayList<>();
            RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");			
            if(username == null || username.trim().isEmpty()) {
                errors.add("Il campo username non può essere vuoto!");
            }
            if(password == null || password.trim().isEmpty()) {
            	errors.add("Il campo password non può essere vuoto!");
			}
            if (!errors.isEmpty()) {
            	request.getSession().setAttribute("errors", errors);
                errors.clear();
            	dispatcherToLoginPage.forward(request, response);
            	return; // note the return statement here!!!
            }
            String hashPassword = toHash(password);
            int id=0;
            String ruolo="";
            String pass="";
            String email="";
            String indirizzo="";
            String nome="";
            String cognome="";
            String telefono="";
            try{
                PreparedStatement ps;
                ResultSet rs;
                Connection cn = mg.getConnection();
                ps = cn.prepareStatement("select * from utente where nome_utente=? and pass=?");
                ps.setString(1, username);
                ps.setString(2, hashPassword);
                rs= ps.executeQuery();
               
                while(rs.next()){
                    id = rs.getInt("id");
                    ruolo = rs.getString("ruolo");
                    pass =  rs.getString("pass");
                    email = rs.getString("email");
                    indirizzo = rs.getString("indirizzo");
                    nome = rs.getString("nome");
                    cognome = rs.getString("cognome");
                    telefono = rs.getString("telefono");
                }                          
            }
            catch(SQLException se){
                request.setAttribute("errormsg", se.getMessage());
                response.sendRedirect("error/error.jsp");
            }  
            //Inizialmenmte con la query sql usiamo nome utente e password per controllare
            // se l'utente è presente nel Database.
            //I dati dell'utente vengono memorizzati solo quando l'accesso è avvenuto
            // correttamente.
            //Dopo aver fatto il controllo settiamo isLoggedIn con yes e se l'utente
            // tenterà di accedere nuovamente verrà reindirizzato verso il login 
            if(hashPassword.matches(pass)){
                User u = new User(id,email,nome,cognome,indirizzo,telefono);
                u.setUsername(username);
                request.getSession().setAttribute("user", u);   
                request.getSession().setAttribute("isloggedIn", "yes"); 
                generateNewSession(request);                
                if(ruolo.matches("admin")){
                    request.getSession().setAttribute("isAdmin", Boolean.TRUE); //inserisco il token nella sessione
                    response.sendRedirect("FetchProductCSide");
                }
                else if (ruolo.matches("cliente")){ //user
                    request.getSession().setAttribute("isAdmin", Boolean.FALSE); //inserisco il token nella sessione
                    response.sendRedirect("index.jsp");
                }               
            }
            else{
                request.getSession().setAttribute("isloggedIn", "no");                
                int retry;
                if(request.getSession().getAttribute("retries")==null) retry=0;  
                else retry = (int) request.getSession().getAttribute("retries");
                if(retry>=3){
                    User u = new User(0,"ni","nil","nil","nil","nil");
                    request.getSession().setAttribute("user", u);
                }
                errors.add("Username o password non validi! Retries availables: 3-"+ retry);
                request.getSession().setAttribute("errors", errors);
                retry++;
                request.getSession().setAttribute("retries", retry);
                dispatcherToLoginPage.forward(request, response);
            }
	}
        ///Generates a new session while saving data from the old one
        // and deletes the old session:
        public void generateNewSession(HttpServletRequest  request){
            //Salviamo la vecchia sessione all'interno di oldsession
            HttpSession oldsession = request.getSession();
            ///Creiamo un elenco di proprietà che utlizzeremo per 
            //immagazzinare gli attributi temporaneamente.
            ///Le proprietà sono composte da coppie chiave-valore quindi 
            // sono ideali per memorizzare i dati della sessione:
            Properties attr = new Properties();         
            //Salviamo i nomi degli attributi in una enumerazione sotto forma di Oggetti
            Enumeration attributeNm = oldsession.getAttributeNames();
            //Non potendo iterare direttamente sugli oggetti-attributi iteriamo
            // sull'elenco dei loro nomi, controllando che attributeNm inizialmente che sia diverso da null.
            if(attributeNm!=null){
                while(attributeNm.hasMoreElements()){
                    //Dichiariamo una chiave che conterrà il valore
                    //dei nomi cicclati degli attributi di sessione
                    String key = (String)attributeNm.nextElement();
                    //Dal risultato di questo ciclo otteniamo che adesso
                    // attr di tipo Properties contiene tutte le coppie 
                    // contenute all'interno cella vecchia sessione.
                    attr.put(key, oldsession.getAttribute(key));
                }
                
                //Elimino la vecchia sessione.
                oldsession.invalidate();
                HttpSession newsession = request.getSession(true);
                
                //Returns an enumeration of the keys in this hashtable.
                // attr.keys restituisce un (Enumerazione delle chiavi contenute all'interno della 
                // della mappa)  il che ci permette di iterare nuovamente sul valore del nome degli 
                // attributi-chiavi:
                attributeNm = attr.keys();
                while(attributeNm.hasMoreElements()){
                    String key = (String)attributeNm.nextElement();
                    newsession.setAttribute(key, attr.get(key));
                    request.getSession().setAttribute(key, attr.get(key));
                }
            }
        }
    
	private String toHash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString( 
                   (hash[i] & 0xFF) | 0x100 )
                .toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return hashString;
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
