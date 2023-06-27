package Model;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Product{
    private List<Product> products;	
    private int id_cliente;
    private double sub_total;
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
        product.setAddedToCart(1);
    }	
    public void deleteProduct(Product product) {
	for(Product prod : products) {
            if(prod.getId() == product.getId()) {
                products.remove(prod);
                product.removeAll();
                this.sub_total = this.sub_total-prod.getPrice();
                break;
            }
        }
    }
    public void setSubTotal(double sub_total){
        this.sub_total = sub_total;
    }
    public double getSubTotal(Product p){
        this.sub_total += p.getPrice()*p.getAddedToCart();
        return this.sub_total;
    }
    public boolean checkIfPresent(Product p){
        return (products.contains(p));      
    }
    public List<Product> getProducts() {
	return  products;
    }
}
