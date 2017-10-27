package com.rcodingschool.carrepair.Exceptions;

public class DuplicateEmailException extends DuplicateUserException {

    public DuplicateEmailException() {
        super();
    }

    public DuplicateEmailException(String msg) {
        super(msg);
    }
}
