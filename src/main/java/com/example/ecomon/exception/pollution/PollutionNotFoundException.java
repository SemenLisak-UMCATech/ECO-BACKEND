package com.example.ecomon.exception.pollution;

public class PollutionNotFoundException extends IllegalArgumentException {

    public PollutionNotFoundException(String message) {
        super(String.format("Pollution with %s doesn't exist in the database", message));
    }
}
