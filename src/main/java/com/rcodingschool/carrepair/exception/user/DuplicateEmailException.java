package com.rcodingschool.carrepair.exception.user;

public class DuplicateEmailException extends DuplicateUserException {

    public DuplicateEmailException() {
        super();
    }

    public DuplicateEmailException(String msg) {
        super(msg);
    }
}
