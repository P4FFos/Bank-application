package src.utils;

<<<<<<< HEAD
<<<<<<< HEAD
import src.ContactCard;

=======
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
public class ContactCardCorporate extends ContactCard{
=======
public class ContactCardCorporate extends ContactCard {
>>>>>>> 117530c168f5743824c28c073355aabbea542e17
    private final String orgNumber;
   

<<<<<<< HEAD
<<<<<<< HEAD
    public ContactCardCorporate(name, orgNumber, email, phoneNumber, streetAddress, zipCode, city){
=======
    public ContactCardCorporate(String name,String orgNumber,String  email, String phoneNumber,String streetAddress,String zipCode,String city){
<<<<<<< HEAD
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
=======
       super(name, email, phonenumber, streetAdress,zipCode,city);
>>>>>>> 7bf1c8347cd3a771532ded4e90535b456820970f
        this.name = name;
=======
    public ContactCardCorporate(String orgNumber, String email, String phoneNumber, String streetAddress, int zipCode, String city) {
        super(email, phoneNumber, streetAddress, zipCode, city);
>>>>>>> 117530c168f5743824c28c073355aabbea542e17
        this.orgNumber = orgNumber;
    }
<<<<<<< HEAD
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
=======
    public String getOrgNumber() {
>>>>>>> 117530c168f5743824c28c073355aabbea542e17
        return orgNumber;
    }

}
