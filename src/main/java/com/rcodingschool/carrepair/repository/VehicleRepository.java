package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    List<Vehicle> findAll();

    List<Vehicle> findByVehicleID(String vehicleID);

    List<Vehicle> findByUserID(Long userID);

    Vehicle save(Vehicle vehicle);

    void deleteByVehicleID(String vehicleID);
}
