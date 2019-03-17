package com.rcodingschool.carrepair.exception.vehicle;

public class DuplicateVehicleException extends Exception {

    private static final String MESSAGE_TEMPLATE = "Plate Number: %s already exists!";


    public DuplicateVehicleException(String vehicleId) {
        super(String.format(MESSAGE_TEMPLATE, vehicleId));
    }
}
