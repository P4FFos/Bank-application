package com.piggybank.app.backend;

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BackendJsonTest {
    public BackendJsonTest() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        jsonExample();
    }
    public static void jsonExample() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File objJsonfile = new File("ui/src/main/java/com/piggybank/app/backend/bankData.json");
        Bank bank = objectMapper.readValue(objJsonfile, Bank.class);
        System.out.println(bank.getCustomerByIdOrSsn("C001").getEmail());

        System.out.println(bank);
        System.out.println(bank.getCustomerByIdOrSsn("C002").getAccounts());
    }
}
