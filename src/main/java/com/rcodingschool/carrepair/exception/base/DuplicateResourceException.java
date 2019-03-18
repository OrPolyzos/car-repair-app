package com.rcodingschool.carrepair.exception.base;

public class DuplicateResourceException extends ResourceException {

    private static final String MESSAGE = "There is already a resource with ID: %s";

    public DuplicateResourceException(Object resourceId) {
        super(String.format(MESSAGE, resourceId));
    }
}
