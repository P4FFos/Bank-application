package src.customers;
import src.utils.ContactCard;

public abstract class User {
    final String userId;
    private String password;
    private ContactCard contactInfo;

    public User(String userId, String password, ContactCard contactInfo) {
        this.userId = userId;
        this.password = password;
        //change this: check for password length: min 8 characters, 1 uppercase, 1 number
        this.contactInfo = contactInfo;
    }

    public String getUserId() {
        return userId;
    }

    public ContactCard getContactInfo() {
        return contactInfo;
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return this.password;
    }

    //create methods for updating contact info (via forwarding from ContactCard once those methods are in place)

}
