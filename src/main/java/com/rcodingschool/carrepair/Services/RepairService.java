package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Repair;

import java.time.LocalDateTime;
import java.util.List;

public interface RepairService {

    List<Repair> findAll();

    List<Repair> findAllByOrderByRepairDateTime();

    List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    Repair findOne(Long repairID);

    List<Repair> findByRepairID(Long repairID);


    List<Repair> findByVehicleID(String vehicleID);

    void save(Repair repair);

    void deleteByRepairID(Long repairID);


}
