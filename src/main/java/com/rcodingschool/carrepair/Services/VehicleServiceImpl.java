package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Vehicle;
import com.rcodingschool.carrepair.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

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

        //repairReository.deleteByVehicleID
        vehicleRepository.deleteByVehicleID(vehicleID);
    }

}
