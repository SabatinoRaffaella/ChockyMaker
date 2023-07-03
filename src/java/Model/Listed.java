package Model;

import java.util.ArrayList;
import java.util.List;

public class Listed {  
    private List<Product> products;	
    public Listed() {
	products = new ArrayList<>();
    }
    public Product fetchById(int id){
        return products.get(id);
    } 
    public void addProduct(Product product) {
	products.add(product);
     }	
    public void deleteProduct(Product product) {
	for(Product prod : products) {
            if(prod.getId() == product.getId()) {
                products.remove(prod);
                break;
            }
        }
    }
    public List<Product> getProducts() {
	return  products;
    }
    
    public int getLastOne(){
        int pos_last = products.size()-1;
        return products.get(pos_last).getId();
    }
    
}
   
