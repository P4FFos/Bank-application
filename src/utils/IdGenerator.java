package src.utils;

public class IdGenerator {
    //employee should start with E (both bank teller and KAM)
    //employee ID should consist of 1 letter (E) and 5 numbers
    //customer ID should consist of 6 numbers
    //increment by 1 for each new ID
    //largest ID number - stored in json file (how to format json file?)
    private final static int MIN_CUSTOMER_ID_LENGTH = 1;
    private final static int MAX_CUSTOMER_ID_LENGTH = 6;
    private final static int MAX_CUSTOMER_ID = 999999;

    public static String generateCustomerID(String lastEmployeeID) throws Exception {
        boolean isEmployeeIDValid = false;
        boolean isEmployeeIDLengthValid = lastEmployeeID.length() >= MIN_CUSTOMER_ID_LENGTH || lastEmployeeID.length() <= MAX_CUSTOMER_ID_LENGTH;
        boolean isEmployeeIDNumbersValid = lastEmployeeID.matches("[0-9]+");
        isEmployeeIDValid = isEmployeeIDLengthValid && isEmployeeIDNumbersValid;
        if (!isEmployeeIDValid) {
            throw new Exception("Invalid employee ID");
        } else {
            int nextID = Integer.parseInt(lastEmployeeID);
            nextID++;
            if (nextID > MAX_CUSTOMER_ID) {
                throw new Exception("Employee ID out of range");
            } else {
                return Integer.toString(nextID);
            }
        }
    }
}
