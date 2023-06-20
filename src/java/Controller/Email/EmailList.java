package Controller.Email;
import java.util.ArrayList;
public class EmailList {
    ArrayList <Email> emails;
    EmailList(){
        emails = new ArrayList<>();
    };

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }
    public void addEmail(Email em){
        this.emails.add(em);
    }
}
