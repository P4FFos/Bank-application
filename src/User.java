package src;

public abstract class User {
    private String userId;
    private String password;
    private ContactCard contactInfo;

    public User(String userId, String password, ContactCard contactCard) {
        this.userId = userId;
        this.password = password;
        contactInfo = contactCard;
    }
}
