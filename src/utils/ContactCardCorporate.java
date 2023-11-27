package src.utils;

<<<<<<< HEAD
import src.ContactCard;

=======
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
public class ContactCardCorporate extends ContactCard{
    private final String orgNumber;
    private String name;

<<<<<<< HEAD
    public ContactCardCorporate(name, orgNumber, email, phoneNumber, streetAddress, zipCode, city){
=======
    public ContactCardCorporate(String name,String orgNumber,String  email, String phoneNumber,String streetAddress,String zipCode,String city){
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
        this.name = name;
        this.orgNumber = orgNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.city = city;
    }
    public String getName(){
<<<<<<< HEAD
        this.name = name;
    }
    public void setName(){
        this.name = newName;
    }
=======
        return name;
    }
    public void setName(){
        this.name = Newname;
    }
    
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
    public String getOrgNumber(){
        return orgNumber;
    }
    
}
