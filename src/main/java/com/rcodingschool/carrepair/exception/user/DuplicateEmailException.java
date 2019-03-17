package com.rcodingschool.carrepair.exception.user;

public class DuplicateEmailException extends DuplicateUserException {

    private static final String MESSAGE = "There is already a user with Email: %s";

    public DuplicateEmailException(String email) {
        super(String.format(MESSAGE, email));
    }
}
