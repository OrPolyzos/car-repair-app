package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairPart;

import java.util.List;

public interface RepairPartService {

    List<RepairPart> findAll();

    List<RepairPart> findAllByRepairID(Long repairID);

    List<RepairPart> findAllByPartID(Long partID);

    void save(RepairPart repairPart);

    void deleteByRepairIDAndPartID(Long repairID, Long PartID);


}
