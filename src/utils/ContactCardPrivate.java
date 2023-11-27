package src.utils;

<<<<<<< HEAD
import src.ContactCard;

=======
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
public class ContactCardPrivate extends ContactCard{
    private final String SSN;

<<<<<<< HEAD
<<<<<<< HEAD
    public ContactCardPrivate(firstName, surname, SSN, email, phoneNumber, streetAddress, zipCode, city){
=======
    public ContactCardPrivate(String firstName, String surname, String SSN,String email,String phoneNumber, String streetAddress, String zipCode, String city){
<<<<<<< HEAD
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
=======
        super(name, email, phoneNumber, streetAddress, zipCode, city);
>>>>>>> 7bf1c8347cd3a771532ded4e90535b456820970f
        this.firstName = firstName;
        this.surname = surname;
=======
    public ContactCardPrivate(String SSN,String email,String phoneNumber, String streetAddress, int zipCode, String city){
        super(email, phoneNumber, streetAddress, zipCode, city);
>>>>>>> 117530c168f5743824c28c073355aabbea542e17
        this.SSN = SSN;
    }
    public String getSSN(){
        return SSN;
    }
}
<<<<<<< HEAD

=======
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
