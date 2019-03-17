package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String> {

    List<Vehicle> findAll();

    Optional<Vehicle> findByVehicleID(String vehicleID);

    List<Vehicle> findAllByUser_Afm(String afm);

    List<Vehicle> findByUserID(Long userID);

    Vehicle save(Vehicle vehicle);

    void deleteByVehicleID(String vehicleID);
}
