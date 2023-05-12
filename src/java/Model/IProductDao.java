package Model;

import java.sql.SQLException;
import java.util.Collection;

public interface IProductDao {
	
	public void doSave(Product product) throws SQLException;

	public boolean doDelete(int code) throws SQLException;

	public Product doRetrieveByKey(int code) throws SQLException;
	
	public Collection<Product> doRetrieveAll(String order) throws SQLException;

}



