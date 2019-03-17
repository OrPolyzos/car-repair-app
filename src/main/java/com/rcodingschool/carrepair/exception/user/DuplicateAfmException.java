package com.rcodingschool.carrepair.exception.user;

import com.rcodingschool.carrepair.exception.base.DuplicateResourceException;

public class DuplicateAfmException extends DuplicateResourceException {

    private static final String MESSAGE = "There is already a user with AFM: %s!";

    public DuplicateAfmException(String afm) {
        super(String.format(MESSAGE, afm));
    }


}
