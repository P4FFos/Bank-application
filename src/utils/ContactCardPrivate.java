package src.utils;

public class ContactCardPrivate extends ContactCard{
    private final String SSN;
    private String firstName;
    private String lastName;
    public ContactCardPrivate(String firstName, String lastName, String SSN,String email,String phoneNumber, String streetAddress, int zipCode, String city){
        super(email, phoneNumber, streetAddress, zipCode, city);
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
    }
    public String getSSN(){
        return SSN;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
}
