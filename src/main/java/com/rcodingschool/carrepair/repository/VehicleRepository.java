package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, String> {

    List<Vehicle> findAllByUser_Afm(String afm);
}
