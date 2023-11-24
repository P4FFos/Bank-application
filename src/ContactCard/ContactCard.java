package src.ContactCard;

public class ContactCard {
    private String name;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private int zipCode;
    private String city;

    public ContactCard(String name, String email, String phoneNumber, String streetAddress, int zipCode, String city) {

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail() {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber() {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return streetAddress;
    }

    public void setStreetAddress() {
        this.streetAddress = streetAddress;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode() {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity() {
        this.city = city;
    }
    
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %s", name, email, phoneNumber, streetAddress, zipCode, city);
    }
}

