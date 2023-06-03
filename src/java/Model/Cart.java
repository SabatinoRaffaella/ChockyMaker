package Model;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Product{
    private List<Product> products;	
    private int id_cliente;

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    public Cart(int id_cliente) {
	products = new ArrayList<>();
        this.id_cliente = id_cliente;
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
}
