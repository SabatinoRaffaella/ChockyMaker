package Model;
public class Order_Details {
    Product p;
    int qt_pr;
    double sub_p;
    String paymn;

    public Order_Details(Product p, int qt_pr, double sub_p, String paymn) {
        this.p = p;
        this.qt_pr = qt_pr;
        this.sub_p = sub_p;
        this.paymn = paymn;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public int getQt_pr() {
        return qt_pr;
    }

    public void setQt_pr(int qt_pr) {
        this.qt_pr = qt_pr;
    }

    public double getSub_p() {
        return sub_p;
    }

    public void setSub_p(double sub_p) {
        this.sub_p = sub_p;
    }

    public String getPaymn() {
        return paymn;
    }

    public void setPaymn(String paymn) {
        this.paymn = paymn;
    }
    
    
}
