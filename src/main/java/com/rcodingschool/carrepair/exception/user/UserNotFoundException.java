package com.rcodingschool.carrepair.exception.user;


import ore.spring.web.initializr.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {

    private static final String ID_MESSAGE_TEMPLATE = "User with ID: %s was not found!";

    private static final String AFM_MESSAGE_TEMPLATE = "User with AFM: %s was not found!";

    public UserNotFoundException(Long userId) {
        super(String.format(ID_MESSAGE_TEMPLATE, userId));
    }

    public UserNotFoundException(String afm) {
        super(String.format(AFM_MESSAGE_TEMPLATE, afm));
    }
}
