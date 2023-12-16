package com.piggybank.app.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        File objJsonfile = new File(filePath);
        return objectMapper.readValue(objJsonfile, Bank.class);
    }
    public static void jsonSerializer() {}
}
