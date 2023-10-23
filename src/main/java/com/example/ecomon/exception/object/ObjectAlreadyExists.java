package com.example.ecomon.exception.object;

public class ObjectAlreadyExists extends IllegalArgumentException {

    public ObjectAlreadyExists(String message) {
        super(String.format("Object with %s already exists in the database", message));
    }

    public ObjectAlreadyExists(String message, Throwable cause) {
        super(String.format("Object with %s already exists in the database", message), cause);
    }
}
