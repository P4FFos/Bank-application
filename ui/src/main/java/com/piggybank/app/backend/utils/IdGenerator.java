package com.piggybank.app.backend.utils;

public class IdGenerator {

	// Constants
    private final static int MAX_CUSTOMER_ID = 999;
    private final static int MAX_EMPLOYEE_ID = 999;
    private final static int MAX_ACCOUNT_ID = 99999;

    // generate customer ID method with length of 3 digits and starting with C
    public static String generateCustomerID(String lastCustomerID) throws Exception {
        int nextID = Integer.parseInt(lastCustomerID.substring(1));
        nextID++;

        if (nextID > MAX_CUSTOMER_ID) {
            throw new Exception("Customer ID out of range");
        }
        int paddingSize = lastCustomerID.length() - 1;

        // E.g. C%05d would pad with zeros in front of nextId until total string length is 5
        return String.format("C%0" + paddingSize + "d", nextID);

    }

    // generate employee ID method with length of 3 digits and starting with E
    public static String generateEmployeeID(String lastEmployeeID) throws Exception {
        int nextID = Integer.parseInt(lastEmployeeID.substring(1));
        nextID++;

        if (nextID > MAX_EMPLOYEE_ID)
            throw new Exception("Employee ID out of range");
        int paddingSize = lastEmployeeID.length() - 1;

        // E.g. E%05d would pad with zeros in front of nextId until total string length is 5
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

        // Checks length of ID, used to pad with this amount of zeros
        int paddingSize = lastAccountId.length() - 1;

        // E.g. A%05d would pad with zeros in front of nextId until total string length is 5
        return String.format("A%0" + paddingSize + "d", nextId);

    }
}
