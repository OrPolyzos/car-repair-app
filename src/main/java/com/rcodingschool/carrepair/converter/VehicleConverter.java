package com.rcodingschool.carrepair.converter;

import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.model.VehicleForm;

public class VehicleConverter {

    public static Vehicle vehicleFormToVehicle(VehicleForm vehicleForm) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleForm.getVehicleID());
        vehicle.setBrand(vehicleForm.getBrand());
        vehicle.setModel(vehicleForm.getModel());
        vehicle.setFuelType(vehicleForm.getFuelType());
        vehicle.setYear(vehicleForm.getYear());
        vehicle.setColor(vehicleForm.getColor());
        vehicle.setUserID(vehicleForm.getUserID());
        return vehicle;
    }

    public static VehicleForm vehicleToVehicleForm(Vehicle vehicle) {
        VehicleForm vehicleForm = new VehicleForm();
        vehicleForm.setVehicleID(vehicle.getId());
        vehicleForm.setBrand(vehicle.getBrand());
        vehicleForm.setModel(vehicle.getModel());
        vehicleForm.setColor(vehicle.getColor());
        vehicleForm.setFuelType(vehicle.getFuelType());
        vehicleForm.setYear(vehicle.getYear());
        vehicleForm.setAfm(vehicle.getUser().getAfm());
        vehicleForm.setUserID(vehicle.getUser().getId());
        return vehicleForm;
    }

}