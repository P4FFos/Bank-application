package src.utils;


public class ContactCardCorporate extends ContactCard {
    private final String orgNumber;
    private String companyName;

    public ContactCardCorporate(String companyName, String orgNumber, String email, String phoneNumber, String streetAddress, int zipCode, String city) {
        super(email, phoneNumber, streetAddress, zipCode, city);
        this.orgNumber = orgNumber;
        this.companyName = companyName;
    }
    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName(){
        this.companyName = companyName;
    }
    
    public String getOrgNumber() {
        return orgNumber;
    }

}
    

