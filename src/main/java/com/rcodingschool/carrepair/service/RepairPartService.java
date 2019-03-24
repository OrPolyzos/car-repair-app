package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.RepairPart;
import spring.web.initializr.base.exception.ResourceException;

import java.util.List;

public interface RepairPartService {

    List<RepairPart> findAllByRepairID(Long repairID);

    List<RepairPart> findAllByPartID(Long partID);

    void save(RepairPart repairPart) throws ResourceException;

    void deleteByRepairIDAndPartID(Long repairID, Long PartID) throws ResourceException;

}
