package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;

import java.util.List;

public interface RepairPartService {

    List<RepairPart> findAll();

    List<RepairPart> findAllByRepairID(Long repairID);

    List<RepairPart> findAllByPartID(Long partID);

    void save(RepairPart repairPart) throws RepairNotFoundException, ResourceNotFoundException;

    void deleteByRepairIDAndPartID(Long repairID, Long PartID) throws RepairNotFoundException, ResourceNotFoundException;


}
