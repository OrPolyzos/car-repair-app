package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Domain.Vehicle;
import com.rcodingschool.carrepair.Model.VehicleForm;

public class VehicleConverter {

    public static Vehicle buildVehicleObject(VehicleForm vehicleForm, User user) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleID(vehicleForm.getVehicleID());
        vehicle.setBrand(vehicleForm.getBrand());
        vehicle.setModel(vehicleForm.getModel());
        vehicle.setFuelType(vehicleForm.getFuelType());
        vehicle.setYear(vehicleForm.getYear());
        vehicle.setColor(vehicleForm.getColor());
        vehicle.setUserID(user.getUserID());
        return vehicle;
    }

    public static VehicleForm buildVehicleFormObject(Vehicle vehicle) {
        VehicleForm vehicleForm = new VehicleForm();
        vehicleForm.setVehicleID(vehicle.getVehicleID());
        vehicleForm.setBrand(vehicle.getBrand());
        vehicleForm.setModel(vehicle.getModel());
        vehicleForm.setColor(vehicle.getColor());
        vehicleForm.setFuelType(vehicle.getFuelType());
        vehicleForm.setYear(vehicle.getYear());
        vehicleForm.setAfm(vehicle.getUser().getAfm());
        vehicleForm.setUserID(vehicle.getUser().getUserID());
        return vehicleForm;
    }

}
