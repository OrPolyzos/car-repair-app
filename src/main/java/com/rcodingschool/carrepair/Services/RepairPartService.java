package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairPart;

import java.util.List;

public interface RepairPartService {

    List<RepairPart> findAll();
    List<RepairPart> findByRepairID(Long repairId);
    RepairPart save(RepairPart repairPart);
//        void deleteByRepairIdOrPartId(Long repairId,Long partId);

}
