package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Repair;
import java.util.List;

public interface RepairService {

    Repair findOne(Long repairID);

    List<Repair> findAll();

    List<Repair> findByRepairID(Long repairID);

    List<Repair> findByVehicleID(String vehicleID);

    void save(Repair repair);

    void deleteByRepairID(Long repairID);


}
