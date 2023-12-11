package com.piggybank.app.backend.exceptions;

public class DuplicateIdException extends Exception {
    public DuplicateIdException(String message) {
        super(message);
    }
}
