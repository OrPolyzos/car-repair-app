package com.rcodingschool.carrepair.exception.user;


public class UserNotFoundException extends UserException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
