package com.rcodingschool.carrepair.exception.user;

import spring.web.initializr.base.exception.DuplicateResourceException;

public class DuplicateEmailException extends DuplicateResourceException {

    private static final String MESSAGE = "There is already a user with Email: %s!";

    public DuplicateEmailException(String email) {
        super(String.format(MESSAGE, email));
    }
}
