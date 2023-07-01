package Model;

import java.util.ArrayList;
import java.util.List;

public class Cart extends Product{
    private List<Product> products;	
    private String id_cliente;
    private double sub_total;
    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
    public Cart(String id_cliente) {
	products = new ArrayList<>();
        this.id_cliente = id_cliente;
    }
    public Product fetchByOrder(int i){
        return this.products.get(i);
    }
    public Product fetchById(int id){
        for(Product prod: products){
            if(prod.getId()==id) return prod;
        }
        return null;
    }
    public boolean emptyCart(){
        this.sub_total=0;
        for(Product prod : this.products){
            prod.removeAll();
        } 
        return this.products.removeAll(products);
    }
    
    public void addProduct(Product product,int id) {
	products.add(product);
        product.setId(id);
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
    public double getSubT(){
        return this.sub_total;
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
