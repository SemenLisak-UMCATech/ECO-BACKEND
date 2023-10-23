package com.example.ecomon.exception.pollutant;

public class PollutantNotFoundException extends IllegalArgumentException {

    public PollutantNotFoundException(String message) {
        super(String.format("Pollutant with %s doesn't exist in the database", message));
    }
}
