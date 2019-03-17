package com.rcodingschool.carrepair.exception.user;

public class DuplicateAfmException extends DuplicateUserException {

    private static final String MESSAGE = "There is already a user with AFM: %s";

    public DuplicateAfmException(String afm) {
        super(String.format(MESSAGE, afm));
    }


}
