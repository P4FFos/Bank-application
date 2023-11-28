package src.utils;

public class ContactCardCorporate extends ContactCard {
    private final String orgNumber;
   

    public ContactCardCorporate(String orgNumber, String email, String phoneNumber, String streetAddress, int zipCode, String city) {
        super(email, phoneNumber, streetAddress, zipCode, city);
        this.orgNumber = orgNumber;
    }
    public String getOrgNumber() {
        return orgNumber;
    }

}
