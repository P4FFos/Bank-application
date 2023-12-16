package com.piggybank.app.backend;

import com.piggybank.app.backend.utils.ContactCard;
import com.piggybank.app.backend.utils.FileHandler;

public class BackendMain {
    public static void main(String[] args) throws Exception {

        String jsonFile = "ui/src/main/java/com/piggybank/app/backend/bankData.json";
        Bank bank = FileHandler.jsonDeserializer(jsonFile);

        // testing the bank
        System.out.println(bank.getCustomerByIdOrSsn("C001").getEmail());
        System.out.println(bank);
        System.out.println(bank.getCustomerByIdOrSsn("C002").getAccounts());
        System.out.println(bank.getBankInfo());
        System.out.println(bank.getTransactionHistory("A00001"));

        ContactCard contactCard = new ContactCard("", "", "", "", "");
        bank.createCustomerPrivate("0801010101", "John", "Doe", "DRAGON1122", contactCard);
        System.out.println(bank.getCustomerByIdOrSsn("0801010101").toString());
    }
}

