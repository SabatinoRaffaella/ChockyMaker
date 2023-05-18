package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class DriverManagerConnectionPool  {			
	private List<Connection> freeDbConnections;

	static {	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
                        Logger logger=null;
                        LogRecord rec=null;
			rec.setMessage("DB driver not found:"+ e.getMessage());  
                        logger.log(rec);
		} 
	}
	
	public DriverManagerConnectionPool() {
		freeDbConnections = new LinkedList<>();
	}
	
	private synchronized Connection createDBConnection() throws SQLException {			
		try{
			Connection newConnection;
			String ip = "localhost";
			String port = "3306";
			String db = "chockymaker";
			String username = "root";
			String password = "sabata";
			try{
			newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
			newConnection.setAutoCommit(false);
			return newConnection;
		
			}catch(Exception f){}
			finally{
			newConnection.close();
			}		
		}
		catch(Exception e){
			Logger logger= new Logger();
            LogRecord rec = new LogRecord();
			rec.setMessage("Failed to connect to database:"+ e.getMessage());  
            logger.log(rec);
		}
		finally{
			newConnection.close();
		}
	}


	public synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();		
		}

		return connection;
	}

	public synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) freeDbConnections.add(connection);
	}
}
