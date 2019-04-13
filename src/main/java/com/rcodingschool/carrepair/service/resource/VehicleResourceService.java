package com.rcodingschool.carrepair.service.resource;

import com.rcodingschool.carrepair.domain.Vehicle;
import com.rcodingschool.carrepair.model.VehicleSearchForm;
import com.rcodingschool.carrepair.repository.VehicleRepository;
import ore.spring.web.initializr.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleResourceService extends ResourceService<Vehicle, VehicleSearchForm, String> {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleResourceService(VehicleRepository vehicleRepository) {
        super(vehicleRepository);
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Iterable<Vehicle> searchBy(VehicleSearchForm vehicleSearchForm) {
        if (vehicleSearchForm.getAfm() == null && vehicleSearchForm.getVehicleID() == null) {
            return findAll();
        } else if (vehicleSearchForm.getVehicleID() != null) {
            return mapOptionalToList(findOptional(vehicleSearchForm.getVehicleID()));
        } else {
            return vehicleRepository.findAllByUser_Afm(vehicleSearchForm.getAfm());
        }
    }

}

