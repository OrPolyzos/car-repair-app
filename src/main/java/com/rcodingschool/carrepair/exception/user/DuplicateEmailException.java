package com.rcodingschool.carrepair.exception.user;

public class DuplicateEmailException extends DuplicateUserException {

    private static final String MESSAGE_TEMPLATE = "Email: %s already exists!";

    public DuplicateEmailException(String email) {
        super(String.format(MESSAGE_TEMPLATE, email));
    }
}
