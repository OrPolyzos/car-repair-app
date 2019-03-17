package com.rcodingschool.carrepair.exception.repair;


public class RepairNotFoundException extends Exception {

    private static final String ID_MESSAGE_TEMPLATE = "Repair with ID: %s was not found!";

    public RepairNotFoundException(Long repairId) {
        super(String.format(ID_MESSAGE_TEMPLATE, repairId));
    }
}
