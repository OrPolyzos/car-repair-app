package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.RepairPart;

import java.util.List;

public interface RepairPartService {

    List<RepairPart> findAll();

    List<RepairPart> findAllByRepairID(Long repairID);

    List<RepairPart> findAllByPartID(Long partID);

    void save(RepairPart repairPart);

    void deleteByRepairIDAndPartID(Long repairID, Long PartID);


}
