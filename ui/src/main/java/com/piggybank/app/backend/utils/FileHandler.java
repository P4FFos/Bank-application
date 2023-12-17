package com.piggybank.app.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.piggybank.app.backend.Bank;

import java.io.File;
import java.io.IOException;

public class FileHandler {
    public static Bank jsonDeserializer(String filePath) throws IOException {
        // jackson class, provides functionality when reading and writing json files
        ObjectMapper objectMapper = new ObjectMapper();
        // to enable java.time.LocalDate module in Jackson, this is needed. LocalDate is used in Transaction class
        objectMapper.registerModule(new JavaTimeModule());

        File jsonfile = new File(filePath);
        return objectMapper.readValue(jsonfile, Bank.class);
    }
    public static void jsonSerializer(String filePath, Bank bank) throws IOException {
        // jackson class, provides functionality when reading and writing json files
        ObjectMapper objectMapper = new ObjectMapper();
        // to enable java.time.LocalDate module in Jackson, this is needed. LocalDate is used in Transaction class
        objectMapper.registerModule(new JavaTimeModule());

        // Create an ObjectWriter with the default pretty printer
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        File jsonFile = new File(filePath);
        objectWriter.writeValue(jsonFile, bank);

    }
}
