package src.utils;

<<<<<<< HEAD
import src.ContactCard;

=======
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
public class ContactCardPrivate extends ContactCard{
    private String firstName;
    private String surname;
    private final String SSN;

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
<<<<<<< HEAD

=======
>>>>>>> 82561ad3e6f96aabab37c323cfb313fa499b93ca
