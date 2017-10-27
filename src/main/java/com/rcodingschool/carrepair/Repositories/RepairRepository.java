package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {

    List<Repair> findAll();

    List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    List<Repair> findTop10ByOrderByRepairDateTimeDesc();

    Repair findOne(Long repairID);

    List<Repair> findByRepairID(Long repairID);

    List<Repair> findAllByRepairDateTimeBetweenAndVehicleID(LocalDateTime localDateTimeStart, LocalDateTime localDatetimeEnd, String vehicleID);


    List<Repair> findByVehicleID(String vehicleID);

    Repair save(Repair repair);

    void deleteByRepairID(Long RepairID);

}
