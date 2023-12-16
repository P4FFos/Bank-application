package com.piggybank.app.backend;

import java.io.File;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.piggybank.app.backend.customers.Customer;
import com.piggybank.app.backend.utils.ContactCard;

public class BackendMain {

    public static Bank jsonExample() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        // to enable java.time.LocalDate module in Jackson, this is needed. Used in Transaction class
        objectMapper.registerModule(new JavaTimeModule());
        File objJsonfile = new File("ui/src/main/java/com/piggybank/app/backend/bankData.json");
        return objectMapper.readValue(objJsonfile, Bank.class);
    }
    public static void main(String[] args) throws Exception {
        Bank bank = jsonExample();

        // testing the bank
        System.out.println(bank.getCustomerByIdOrSsn("C001").getEmail());
        System.out.println(bank);
        System.out.println(bank.getCustomerByIdOrSsn("C002").getAccounts());
        System.out.println(bank.getCustomerIdCounter());
        System.out.println(bank.getBankInfo());
        System.out.println(bank.getTransactionHistory("A00001"));
    }
}

