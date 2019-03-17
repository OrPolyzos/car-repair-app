package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.exception.base.ResourceException;
import com.rcodingschool.carrepair.exception.vehicle.DuplicateVehicleException;
import com.rcodingschool.carrepair.model.VehicleSearchForm;
import com.rcodingschool.carrepair.repository.VehicleRepository;
import com.rcodingschool.carrepair.service.base.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleResourceService extends ResourceService<Vehicle, String> {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleResourceService(VehicleRepository vehicleRepository) {
        super(vehicleRepository);
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }


    public List<Vehicle> searchForVehicles(VehicleSearchForm vehicleSearchForm) {
        if (vehicleSearchForm.getAfm() == null && vehicleSearchForm.getVehicleID() == null) {
            return findAll();
        } else if (vehicleSearchForm.getVehicleID() != null) {
            return mapOptionalToList(findOptional(vehicleSearchForm.getVehicleID()));
        } else {
            return vehicleRepository.findAllByUser_Afm(vehicleSearchForm.getAfm());
        }
    }

    @Override
    protected void validateBeforeInsertOrThrow(Vehicle vehicle) throws ResourceException {
        if (vehicleRepository.findByVehicleID(vehicle.getVehicleID()).isPresent()) {
            throw new DuplicateVehicleException(vehicle.getVehicleID());
        }
    }

    @Override
    protected void validateBeforeUpdateOrThrow(Vehicle entity) throws ResourceException {
        // TODO Revisit this to actually confirm it should be ignored
        // ignored
    }
}

