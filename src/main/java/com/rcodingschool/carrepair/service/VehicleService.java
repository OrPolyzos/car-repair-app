package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> findAll();

    List<Vehicle> findByUserID(Long userID);

    List<Vehicle> findByVehicleID(String vehicleID);

    void save(Vehicle vehicle);

    void deleteByVehicleID(String vehicleID);
}
