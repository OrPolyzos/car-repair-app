package com.rcodingschool.carrepair.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidCredentialsException extends AuthenticationException {

    private static final String MESSAGE = "Invalid credentials. Afm/Email: %s";

    public InvalidCredentialsException(String afmOrEmail) {
        super(String.format(MESSAGE, afmOrEmail));
    }

}
