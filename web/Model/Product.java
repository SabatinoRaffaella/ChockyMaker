package Model;

import java.io.Serializable;
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
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

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
	public int getQuantity(){
		return this.quantity;
	}
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double price){
		this.price=price;
	}
    @Override
    public String toString(){
        return "\n Here are all info: Id is: "+getId()+"\n Brand is: "+getBrand()+"\n Type of the product: "+getName()+"\n"
                +"Description:"+"\n"+ getDescription()+"\n"+ " Quantity: "+ getQuantity()+"\n";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    
}
