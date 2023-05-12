package Model;
public class Product {
    private int id;
    private String description;
    private String name;
    private String brand;
    private int quantity;
    private double price;

    public Product() {
    id = -1;
    name = "";
    description = "";
    quantity = 0;
    brand="";
    price =0.0;

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
                +"Description:"+"\n"+ getDescription()+"\n";
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

    public void setType(String type) {
        this.name = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
