package src.utils;

public class IdGenerator {
    private final static int MIN_CUSTOMER_ID_LENGTH = 1;
    private final static int MAX_CUSTOMER_ID_LENGTH = 6;
    private final static int MAX_CUSTOMER_ID = 999999;

    private final static int MAX_EMPLOYEE_ID = 99999;

    public static String generateCustomerID(String lastCustomerID) throws Exception {
        boolean isCustomerIDValid = false;
        boolean isCustomerIDLengthValid = lastCustomerID.length() >= MIN_CUSTOMER_ID_LENGTH || lastCustomerID.length() <= MAX_CUSTOMER_ID_LENGTH;
        boolean isCustomerIDNumbersValid = lastCustomerID.matches("[0-9]+");
        isCustomerIDValid = isCustomerIDLengthValid && isCustomerIDNumbersValid;

        int nextID = Integer.parseInt(lastCustomerID);
        nextID++;

        return Integer.toString(nextID);
    }

    public static String generateEmployeeID(String lastEmployeeID) throws Exception {
        int nextID = Integer.parseInt(lastEmployeeID.substring(1));
        nextID++;
        if (nextID > MAX_EMPLOYEE_ID) // if the next employee ID is out of range
            throw new Exception("Employee ID out of range");
        return "e" + nextID;
    }
}
