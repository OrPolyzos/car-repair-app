package com.rcodingschool.carrepair.exception.base;

public class ResourceNotFoundException extends ResourceException {

    private static final String MESSAGE = "Failed to find resource with ID: %s";

    public ResourceNotFoundException(Object id) {
        super(String.format(MESSAGE, id));
    }
}
