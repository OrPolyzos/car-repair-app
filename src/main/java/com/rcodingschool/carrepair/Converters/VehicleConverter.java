package com.rcodingschool.carrepair.Converters;

import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Domain.Vehicle;
import com.rcodingschool.carrepair.Model.UserForm;
import com.rcodingschool.carrepair.Model.VehicleForm;

public class VehicleConverter {

    public static Vehicle buildVehicleObject(VehicleForm vehicleForm) {
        return new Vehicle(vehicleForm.getVehicleID(), vehicleForm.getBrand(), vehicleForm.getModel(), vehicleForm.getFuelType(), vehicleForm.getYear(), vehicleForm.getColor(), vehicleForm.getUserID());
    }

    public static VehicleForm buildVehicleFormObject(Vehicle vehicle) {
        VehicleForm vehicleForm = new VehicleForm();
        vehicleForm.setVehicleID(vehicle.getVehicleID());
        vehicleForm.setBrand(vehicle.getBrand());
        vehicleForm.setModel(vehicle.getModel());
        vehicleForm.setColor(vehicle.getColor());
        vehicleForm.setFuelType(vehicle.getFuelType());
        vehicleForm.setYear(vehicle.getYear());
        vehicleForm.setUserID(vehicle.getUserID());
        return vehicleForm;
    }

}
