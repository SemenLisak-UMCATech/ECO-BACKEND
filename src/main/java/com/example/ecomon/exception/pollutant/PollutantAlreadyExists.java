package com.example.ecomon.exception.pollutant;

public class PollutantAlreadyExists extends IllegalArgumentException {

    public PollutantAlreadyExists(String message) {
        super(String.format("Pollutant with %s already exists in the database", message));
    }

    public PollutantAlreadyExists(String message, Throwable cause) {
        super(String.format("Pollutant with %s already exists in the database", message), cause);
    }
}
