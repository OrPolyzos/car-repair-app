package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {

    List<Repair> findAll();

    List<Repair> findAllByOrderByRepairDateTime();

    List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    Repair findOne(Long repairID);

    List<Repair> findByRepairID(Long repairID);

    List<Repair> findByRepairStatus(String repairStatus);

    List<Repair> findByVehicleID(String vehicleID);

    List<Repair> findByRepairTypeID(String RepairTypeID);





    Repair save(Repair repair);

    void deleteByRepairID(Long RepairID);

}
