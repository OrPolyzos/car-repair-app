package com.rcodingschool.carrepair.exception.user;


public class UserNotFoundException extends UserException {

    private static final String MESSAGE_TEMPLATE = "User with ID: %s was not found!";

    public UserNotFoundException(Long userId) {
        super(String.format(MESSAGE_TEMPLATE, userId));
    }
}
