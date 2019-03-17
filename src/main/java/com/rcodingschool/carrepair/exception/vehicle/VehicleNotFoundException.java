package com.rcodingschool.carrepair.exception.vehicle;


public class VehicleNotFoundException extends Exception {

    private static final String MESSAGE_TEMPLATE = "Vehicle with Plate Number: %s was not found!";

    public VehicleNotFoundException(String plateNumber) {
        super(String.format(MESSAGE_TEMPLATE, plateNumber));
    }
}
