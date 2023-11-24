package src;

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
