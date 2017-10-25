package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairPart;

import java.util.List;

public interface RepairPartService {

    RepairPart findOne(Long repairID);

    List<RepairPart> findAll();


    List<RepairPart> findAllByRepairID(Long repairID);

    void save(RepairPart repairPart);

    void deleteByRepairID(Long repairID);


}
