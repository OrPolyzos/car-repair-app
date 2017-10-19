package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {

    List<Repair> findByRepairID(Long repairID);

    List<Repair> findByRepairStatus(String repairStatus);

    List<Repair> findByVehicleID(String vehicleID);

    List<Repair> findByRepairTypeID(String RepairTypeID);

    List<Repair> findByRepairDateAndRepairTime(Date repairDate, Time repairTime);

    List<Repair> findAll();

    Repair save(Repair repair);

    void deleteByRepairID(Long RepairID);

}
