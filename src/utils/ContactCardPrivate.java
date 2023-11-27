package src.utils;

public class ContactCardPrivate extends ContactCard{
    private String firstName;
    private String surname;
    private final String SSN;

    public ContactCardPrivate(String firstName, String surname, String SSN,String email,String phoneNumber, String streetAddress, String zipCode, String city){
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
