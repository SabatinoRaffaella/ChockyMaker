package Model;

import java.util.ArrayList;

/**
 *
 * @author raffy
 */
public class Order {
    private String dateOrder;
    private int numItem;
    private String description;
    private ArrayList product_list;
    
    
    public Order(){
        this.dateOrder = null;
        this.numItem = 0;
        this.description = null;
        this.product_list = new ArrayList();
    }
    
    public ArrayList addOrderedItem(Product po){
        this.product_list.add(po);
        this.numItem++;
        return this.product_list;
    }
    
    @Override
    public String toString(){
        return "Order was sent in date: "+getDateOrder()
                +" with a total of "+getNumItem()+" item ordered \n"
                +" extra description of the products: \n"+getDescription()+"\n"
                +"\n"+ alli();
    }
    public Order(String date,int numitem,String desc){
        this.dateOrder = date;
        this.numItem = numitem;
        this.description = desc;
   
    }
    
    public String alli(){
        int i=0;
        Product p =(Product) this.product_list.get(i);
        String s="";
        while(i<this.numItem){
            s = s.concat(p.toString());
            
            i++;
        }
        return s;
    }
    
    public String getDateOrder(){
        return this.dateOrder;
    }
    
    public int getNumItem(){
        return this.numItem;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDateOrder(String dateOrder){
        this.dateOrder = dateOrder;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
