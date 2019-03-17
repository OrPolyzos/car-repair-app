package com.rcodingschool.carrepair.exception.vehicle;


import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;

public class VehicleNotFoundException extends ResourceNotFoundException {

    private static final String MESSAGE_TEMPLATE = "Vehicle with Plate Number: %s was not found!";

    public VehicleNotFoundException(String plateNumber) {
        super(String.format(MESSAGE_TEMPLATE, plateNumber));
    }
}
