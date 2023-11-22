package src;

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
public class ContactCardPrivate extends ContactCard{
    private String firstName;
    private String surname;
    private final String SSN;

    public ContactCardPrivate(firstName, surname, SSN, email, phoneNumber, streetAddress, zipCode, city){
        this.firstName = firstName;
        this.surname = surname;
        this.SSN = SSN;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getSurname(){
        return surname;
    }
    public String getSSN(){
        return SSN;
    }
}

public class ContactCardCorporate extends ContactCard{
    private final String orgNumber;
    @Override
    private String name;

    public ContactCardCorporate(name, orgNumber, email, phoneNumber, streetAddress, zipCode, city){
        this.name = name;
        this.orgNumber = orgNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }
    public String getName(){
        this.name = name;
    }
    public void setName(){
        this.name = newName;
    }
    public String getOrgNumber(){
        return orgNumber;
    }
    
}