package src.utils;

public class IdGenerator {
    private final static int MIN_CUSTOMER_ID_LENGTH = 1;
    private final static int MAX_CUSTOMER_ID_LENGTH = 6;
    private final static int MAX_CUSTOMER_ID = 999999;

    private final static int MIN_EMPLOYEE_ID_LENGTH = 2;
	private final static int MAX_EMPLOYEE_ID_LENGTH = 6;
	private final static int MAX_EMPLOYEE_ID = 99999;

    public static String generateCustomerID(String lastCustomerID) throws Exception {
        boolean isEmployeeIDValid = false;
        boolean isEmployeeIDLengthValid = lastCustomerID.length() >= MIN_CUSTOMER_ID_LENGTH || lastCustomerID.length() <= MAX_CUSTOMER_ID_LENGTH;
        boolean isEmployeeIDNumbersValid = lastCustomerID.matches("[0-9]+");
        isEmployeeIDValid = isEmployeeIDLengthValid && isEmployeeIDNumbersValid;
        if (!isEmployeeIDValid) {
            throw new Exception("Invalid customer ID");
        } else {
            int nextID = Integer.parseInt(lastCustomerID);
            nextID++;
            if (nextID > MAX_CUSTOMER_ID) {
                throw new Exception("Customer ID out of range");
            } else {
                return Integer.toString(nextID);
            }
        }
    }
	
	public static String generateEmployeeID(String lastEmployeeID) throws Exception{
		boolean isEmployeeIDValid = false;
		boolean isEmployeeIDStartValid = lastEmployeeID.startsWith("e");
		boolean isEmployeeIDLengthValid = lastEmployeeID.length() >= MIN_EMPLOYEE_ID_LENGTH || lastEmployeeID.length() <= MAX_EMPLOYEE_ID_LENGTH;
		boolean isEmployeeIDNumbersValid = lastEmployeeID.substring(1).matches("[0-9]+");
		isEmployeeIDValid = isEmployeeIDStartValid && isEmployeeIDLengthValid && isEmployeeIDNumbersValid;
		if(!isEmployeeIDValid) // if the last employee ID is not in the correct format
			throw new Exception("Invalid employee ID");
		int nextID = Integer.parseInt(lastEmployeeID.substring(1));
		nextID++;
		if(nextID > MAX_EMPLOYEE_ID) // if the next employee ID is out of range
			throw new Exception("Employee ID out of range");
		return "e" + nextID;
	}
}
