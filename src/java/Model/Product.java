package Model;
public class Product {
    private String id;
    private String description;
    private String type;
    private String brand;

    public Product(String description, String type, String brand,String id) {
        this.description = description;
        this.type = type;
        this.brand = brand;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String toString(){
        return "\n Here are all info: Id is: "+getId()+"\n Brand is: "+getBrand()+"\n Type of the product: "+getType()+"\n"
                +"Description:"+"\n"+ getDescription()+"\n";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    
}
