package com.rcodingschool.carrepair.Exceptions;

public class DuplicateUserException extends Exception{

    public DuplicateUserException() {
        super();
    }

    public DuplicateUserException(String msg) {
        super(msg);
    }

}
