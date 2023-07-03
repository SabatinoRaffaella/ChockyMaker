package Model;
import java.time.LocalDate;
import java.util.ArrayList;
public class Order {
    private int id_order;
    private ArrayList<Order_Details> dt;
    private User user;
    LocalDate order_date;
    LocalDate order_sentd;
    String address;
    
    public Order(int id_order, ArrayList dt, User user, LocalDate order_date, LocalDate order_sentd, String address) {
        this.id_order = id_order;
        this.dt = dt;
        this.user = user;
        this.order_date = order_date;
        this.order_sentd = order_sentd;
        this.address = address;
    }
    
    
    /*public ArrayList addOrderedItem(Product po){
        this.product_list.add(po);
        this.numItem++;
        return this.product_list;
    }
    *//*
    @Override
    public String toString(){
        return "Order was sent in date: "+getDateOrder()
                +" with a total of "+getNumItem()+" item ordered \n"
                +" extra description of the products: \n"+getDescription()+"\n"
                +"\n"+ alli();
    }*/
    /*
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
    */

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public ArrayList getDt() {
        return dt;
    }

    public void setDt(ArrayList dt) {
        this.dt = dt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public LocalDate getOrder_sentd() {
        return order_sentd;
    }

    public void setOrder_sentd(LocalDate order_sentd) {
        this.order_sentd = order_sentd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}
