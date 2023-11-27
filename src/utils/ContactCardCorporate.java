package src.utils;

public class ContactCardCorporate extends ContactCard{
    private final String orgNumber;
    private String name;

    public ContactCardCorporate(String name,String orgNumber,String  email, String phoneNumber,String streetAddress,String zipCode,String city){
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
