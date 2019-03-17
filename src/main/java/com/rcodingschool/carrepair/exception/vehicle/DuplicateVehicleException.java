package com.rcodingschool.carrepair.exception.vehicle;

import com.rcodingschool.carrepair.exception.base.DuplicateResourceException;

public class DuplicateVehicleException extends DuplicateResourceException {

    private static final String MESSAGE = "There is already a vehicle with Plate Number: %s!";

    public DuplicateVehicleException(String vehicleId) {
        super(String.format(MESSAGE, vehicleId));
    }
}
