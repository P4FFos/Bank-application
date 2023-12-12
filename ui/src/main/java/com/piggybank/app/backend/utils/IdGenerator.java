package com.piggybank.app.backend.utils;

public class IdGenerator {
    private final static int MAX_CUSTOMER_ID = 999;
    private final static int MAX_EMPLOYEE_ID = 999;
    private final static int MAX_ACCOUNT_ID = 99999;

    // generate customer ID method with length of 3 digits and starting with C
    public static String generateCustomerID(String lastCustomerID) throws Exception {
        int nextID = Integer.parseInt(lastCustomerID.substring(1));
        nextID++;

        if (nextID > MAX_CUSTOMER_ID) {
            throw new Exception("Customer ID out of range");
        } else {
            int paddingSize = lastCustomerID.length() - Integer.toString(nextID).length();
            return String.format("C%0" + paddingSize + "d", nextID);
        }
    }

    // generate employee ID method with length of 3 digits and starting with E
    public static String generateEmployeeID(String lastEmployeeID) throws Exception {
        int nextID = Integer.parseInt(lastEmployeeID.substring(1));
        nextID++;

        if (nextID > MAX_EMPLOYEE_ID)
            throw new Exception("Employee ID out of range");
        int paddingSize = lastEmployeeID.length() - Integer.toString(nextID).length();
        return String.format("E%0" + paddingSize + "d", nextID);
    }

    // Increments an account ID with one unit based on lastAccountId used
    public static String generateAccountId(String lastAccountId) throws Exception {
        // Parses integer from string lastAccountId
        int nextId = Integer.parseInt(lastAccountId.substring(1));
        nextId++;

        if (nextId > MAX_ACCOUNT_ID) {
            throw new Exception("Account ID out of range");
        }

        // Checks length of integer, used to pad with this amount of zeros
        int paddingSize = lastAccountId.length() - Integer.toString(nextId).length();

        // E.g. A%04d would give "A0000" plus int of nextID
        return String.format("A%0" + paddingSize + "d", nextId);

    }
}
