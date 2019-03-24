package com.rcodingschool.carrepair.exception.user;


import spring.web.initializr.base.exception.DuplicateResourceException;

public class DuplicateAfmException extends DuplicateResourceException {

    private static final String MESSAGE = "There is already a user with AFM: %s!";

    public DuplicateAfmException(String afm) {
        super(String.format(MESSAGE, afm));
    }


}
