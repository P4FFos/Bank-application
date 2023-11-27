package src.utils;

public class ContactCardPrivate extends ContactCard{
    private final String SSN;

    public ContactCardPrivate(String SSN,String email,String phoneNumber, String streetAddress, int zipCode, String city){
        super(email, phoneNumber, streetAddress, zipCode, city);
        this.SSN = SSN;
    }
    public String getSSN(){
        return SSN;
    }
}
