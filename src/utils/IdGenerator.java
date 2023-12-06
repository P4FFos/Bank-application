package src.utils;

public class IdGenerator {
    private final static int MAX_CUSTOMER_ID = 999999;

    private final static int MAX_EMPLOYEE_ID = 99999;

    public static String generateCustomerID(String lastCustomerID) throws Exception {
        int nextID = Integer.parseInt(lastCustomerID);
        nextID++;

        if (nextID > MAX_CUSTOMER_ID) {
            throw new Exception("Customer ID out of range");
        } else {
            return Integer.toString(nextID);
        }
    }

    public static String generateEmployeeID(String lastEmployeeID) throws Exception {
        int nextID = Integer.parseInt(lastEmployeeID.substring(1));
        nextID++;
        if (nextID > MAX_EMPLOYEE_ID) // if the next employee ID is out of range
            throw new Exception("Employee ID out of range");
        return "e" + nextID;
    }
}
