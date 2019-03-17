package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {

    List<Repair> findAll();

    List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd);

    List<Repair> findTop10ByOrderByRepairDateTimeDesc();

    Repair findOne(Long repairID);

    Optional<Repair> findByRepairID(Long repairID);

    List<Repair> findAllByRepairDateTimeBetweenAndVehicleID(LocalDateTime localDateTimeStart, LocalDateTime localDatetimeEnd, String vehicleID);

    List<Repair> findByVehicleID(String vehicleID);

    Repair save(Repair repair);

    void deleteByRepairID(Long RepairID);
}
