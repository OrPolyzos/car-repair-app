package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findByUserID(Long userID) {
        return vehicleRepository.findByUserID(userID);
    }

    @Override
    public List<Vehicle> findByVehicleID(String vehicleID) {
        return vehicleRepository.findByVehicleID(vehicleID);
    }

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteByVehicleID(String vehicleID) {
        vehicleRepository.deleteByVehicleID(vehicleID);
    }

}
