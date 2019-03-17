package com.rcodingschool.carrepair.exception.user;

import com.rcodingschool.carrepair.exception.base.ResourceException;

public class DuplicateUserException extends ResourceException {

    public DuplicateUserException(String msg) {
        super(msg);
    }

}
