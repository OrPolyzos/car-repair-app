package com.rcodingschool.carrepair.exception.user;

public class DuplicateAfmException extends DuplicateUserException {

    private static final String MESSAGE_TEMPLATE = "AFM: %s already exists!";

    public DuplicateAfmException(String afm) {
        super(String.format(MESSAGE_TEMPLATE, afm));
    }


}
