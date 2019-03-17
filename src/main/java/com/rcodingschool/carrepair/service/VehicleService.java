package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.exception.vehicle.DuplicateVehicleException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
import com.rcodingschool.carrepair.model.VehicleSearchForm;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> findAll();

    Vehicle findOne(String vehicleID) throws VehicleNotFoundException;

    Optional<Vehicle> findByVehicleID(String vehicleID);

    void insert(Vehicle vehicle) throws DuplicateVehicleException;

    void update(Vehicle vehicle);

    void deleteByVehicleID(String vehicleID) throws VehicleNotFoundException;

    List<Vehicle> searchForVehicles(VehicleSearchForm vehicleSearchForm);
}
