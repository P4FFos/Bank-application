package com.piggybank.app.backend;

import com.piggybank.app.backend.utils.FileHandler;

import java.time.LocalDate;

public class BackendMain {
    public static void main(String[] args) throws Exception {

        String jsonFile = "ui/src/main/java/com/piggybank/app/backend/bankData.json";
        Bank bank = FileHandler.jsonDeserializer(jsonFile);

        // testing the bank
        System.out.println(bank.getCustomerByIdOrSsn("C001").getEmail());
        System.out.println(bank);
        System.out.println(bank.getCustomerByIdOrSsn("C002").getAccounts());
        System.out.println(bank.getContactInfo());
        System.out.println(bank.getTransactionHistory("A00001"));

        // will add transaction in json file
        bank.transfer("A00003", "A00001", 400,"Money from Jane", LocalDate.now());

        String saveFile = "ui/src/main/java/com/piggybank/app/backend/bankDataBackendMain.json";

        FileHandler.jsonSerializer(saveFile, bank);


    }
}

