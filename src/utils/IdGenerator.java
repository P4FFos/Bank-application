package src.utils;

public class IdGenerator {
    //employee should start with E (both bank teller and KAM)
    //employee ID should consist of 1 letter (E) and 5 numbers
    //customer ID should consist of 6 numbers
    //increment by 1 for each new ID
    //largest ID number - stored in json file (how to format json file?)

    public void customerIDGenerator() throws Exception {
        String customerId = String.format("%06d", lastFoundId);
        customerId = lastFoundId + 1;

        // create lastFoundID method in bank
    }
}
