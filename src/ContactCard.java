package src;

public class ContactCard {
     private String email;
    private String phoneNumber;
    private String streetAddress;
    private int zipCode;
    private String city;

    public ContactCard(String firstName, String surname, String email, String phoneNumber, String streetAddress, int zipCode, String city) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %d, %s", firstName, surname, email, phoneNumber, streetAddress, zipCode, city);
    }
}
