package com.rcodingschool.carrepair.exception.part;


public class PartNotFoundException extends Exception {

    private static final String ID_MESSAGE_TEMPLATE = "Part with ID: %s was not found!";

    public PartNotFoundException(Long repairId) {
        super(String.format(ID_MESSAGE_TEMPLATE, repairId));
    }
}
