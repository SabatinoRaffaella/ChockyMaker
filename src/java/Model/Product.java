package Model;
import java.io.Serializable;
public class Product implements Serializable{	
    private static final long serialVersionUID = 1L;
    private int id;
    private int addedToCart;
    private String description;
    private String name;
    private String brand;
    private int quantity;
    private double amount;
    private double price;
    private String img;
    public Product() {
        addedToCart= 0;
        id = -1;
        name = "";
	description = "";
	quantity = 0;
	brand="";
	price =0.0;
	amount=0.0;
	img="";
    }
    public Product(int id,String name, String desc, String brand, double p, int q,double pr_amouunt,String imgpath){
        this.id = id;
        this.name= name;
        this.description= desc;
        this.quantity = q;
        this.price = p;
        this.amount = pr_amouunt;
        this.img = imgpath;
        this.brand = brand;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return "\n Here are all info: Id is: "+getId()+"\n Brand is: "+getBrand()+"\n Type of the product: "+getName()+"\n"
                +"Description:"+"\n"+ getDescription()+"\n"+ "Quantity is: "+getAddedToCart()+"\n";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getAddedToCart() {
        return addedToCart;
    }
    public void removeAll(){
        this.addedToCart = 0;
    }
    public void setAddedToCart(int addedToCart) {
        this.addedToCart += addedToCart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
}
