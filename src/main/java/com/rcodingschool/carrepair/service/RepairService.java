package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.exception.vehicle.VehicleNotFoundException;
import com.rcodingschool.carrepair.model.RepairSearchForm;

import java.time.LocalDateTime;
import java.util.List;

public interface RepairService {

    List<Repair> findAll();

    List<Repair> findAllByRepairDateTimeBetweenAndVehicleID(LocalDateTime localDateTimeStart, LocalDateTime localDatetimeEnd, String vehicleID);

    List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    List<Repair> findTop10ByOrderByRepairDateTimeDesc();

    Repair findOne(Long repairID);

    Repair findByRepairID(Long repairID) throws RepairNotFoundException;

    List<Repair> findByVehicleID(String vehicleID);

    void save(Repair repair) throws VehicleNotFoundException;

    void saveAfterDeletedPart(Repair repair);

    void deleteByRepairID(Long repairID) throws RepairNotFoundException;

    List<Repair> searchRepairs(RepairSearchForm repairSearchForm);
}
