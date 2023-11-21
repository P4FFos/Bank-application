package src.customers;

import src.utils.ContactCard;

public abstract class User {
    final private String userId;
    private String password;
    private ContactCard contactInfo;

    public User(String userId, String password, ContactCard contactCard) {
        this.userId = userId;
        this.password = password;
        contactInfo = contactCard;
    }
    public String getUserId(){
        return userId;
    }
    public void changePassword(String newPassword){
        this.password = newPassword;
    }
    public void changeContactInfo(ContactCard contactInfo){
        this.contactInfo = contactInfo;
    }
}
