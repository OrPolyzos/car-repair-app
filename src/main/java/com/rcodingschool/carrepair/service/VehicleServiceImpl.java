package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.exception.vehicle.DuplicateVehicleException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
import com.rcodingschool.carrepair.model.VehicleSearchForm;
import com.rcodingschool.carrepair.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Vehicle findOne(String vehicleID) throws VehicleNotFoundException {
        return findByVehicleID(vehicleID).orElseThrow(() -> new VehicleNotFoundException(vehicleID));
    }

    @Override
    public Optional<Vehicle> findByVehicleID(String vehicleID) {
        return vehicleRepository.findByVehicleID(vehicleID);
    }

    @Override
    public void insert(Vehicle vehicle) throws DuplicateVehicleException {
        if (vehicleRepository.findByVehicleID(vehicle.getVehicleID()).isPresent()) {
            throw new DuplicateVehicleException(vehicle.getVehicleID());
        }
        vehicleRepository.save(vehicle);
    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteByVehicleID(String vehicleID) throws VehicleNotFoundException {
        if (!vehicleRepository.findByVehicleID(vehicleID).isPresent()) {
            throw new VehicleNotFoundException(vehicleID);
        }
        vehicleRepository.deleteByVehicleID(vehicleID);
    }

    @Override
    public List<Vehicle> searchForVehicles(VehicleSearchForm vehicleSearchForm) {
        if (vehicleSearchForm.getAfm() == null && vehicleSearchForm.getVehicleID() == null) {
            return findAll();
        } else if (vehicleSearchForm.getVehicleID() != null) {
            return findByVehicleID(vehicleSearchForm.getVehicleID()).map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            return vehicleRepository.findAllByUser_Afm(vehicleSearchForm.getAfm());
        }
    }
}
