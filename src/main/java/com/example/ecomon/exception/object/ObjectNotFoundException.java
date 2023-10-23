package com.example.ecomon.exception.object;

public class ObjectNotFoundException extends IllegalArgumentException {

    public ObjectNotFoundException(String message) {
        super(String.format("Object with %s doesn't exist in the database", message));
    }
}
