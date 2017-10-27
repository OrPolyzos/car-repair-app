package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Repair;

import java.time.LocalDateTime;
import java.util.List;

public interface RepairService {

    List<Repair> findAll();

    List<Repair> findAllByRepairDateTimeBetweenAndVehicleID(LocalDateTime localDateTimeStart, LocalDateTime localDatetimeEnd, String vehicleID);

    List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    List<Repair> findTop10ByOrderByRepairDateTimeDesc();

    Repair findOne(Long repairID);

    List<Repair> findByRepairID(Long repairID);


    List<Repair> findByVehicleID(String vehicleID);

    void save(Repair repair);

    void saveAfterDeletedPart(Repair repair);

    void deleteByRepairID(Long repairID);


}
