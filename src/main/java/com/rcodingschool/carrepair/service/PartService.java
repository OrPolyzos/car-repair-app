package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import com.rcodingschool.carrepair.exception.part.PartNotFoundException;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
import com.rcodingschool.carrepair.model.PartSearchForm;

import java.util.List;
import java.util.Optional;

public interface PartService {

    Part findOne(Long partID);

    List<Part> findAll();

    Optional<Part> findByPartID(Long partID);

    List<Part> findAllByPartPriceBetween(Long partPriceStart, Long partPriceEnd);

    void save(Part part) throws RepairNotFoundException, VehicleNotFoundException, ResourceNotFoundException;

    void deleteByPartID(Long partID) throws RepairNotFoundException, PartNotFoundException;

    List<Part> searchForParts(PartSearchForm partSearchForm);
}
