package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairPart;

import java.util.List;

public interface RepairPartService {

    List<RepairPart> findAll();
    List<RepairPart> findByRepairID(Long repairID);
    RepairPart save(RepairPart repairPart);
    void deleteByRepairIdOrPartId(Long repairID,Long partID);

}
