package src.utils;

public class IdGenerator {
    private final static int MAX_CUSTOMER_ID = 99999;
    private final static int MAX_EMPLOYEE_ID = 99999;

    public static String generateCustomerID(String lastCustomerID) throws Exception {
        int nextID = Integer.parseInt(lastCustomerID.substring(1));

        if (nextID > MAX_CUSTOMER_ID) {
            throw new Exception("Customer ID out of range");
        } else {
            nextID++;
            return "c" + nextID;
        }
    }

    public static String generateEmployeeID(String lastEmployeeID) throws Exception {
        int nextID = Integer.parseInt(lastEmployeeID.substring(1));

        nextID++;
        if (nextID > MAX_EMPLOYEE_ID) // if the next employee ID is out of range
            throw new Exception("Employee ID out of range");
        return "e" + nextID;
    }

    // Increments an account ID with one unit based on lastAccountId used
    public static String generateAccountId(String lastAccountId) throws Exception {
        final int MAX_ACCOUNT_ID = 999999999;
        // Parses integer from string lastAccountId
        int nextId = Integer.parseInt(lastAccountId.substring(1));
        nextId++;

        if(nextId > MAX_ACCOUNT_ID) {
            throw new Exception("Account ID out of range");
        }

        // Checks lenght of integer, used to pad with this amount of zeros
        int paddingSize = 10 - Integer.toString(nextId).length();

        // E.g. A%04d would give "A0000" plus int of nextID
        return String.format("a%0" + paddingSize + "d", nextId);

    }
}
