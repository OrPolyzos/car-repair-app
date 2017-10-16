package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.Repair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends CrudRepository<Repair, Long> {
    List<Repair> findAll();
    List<Repair> findByAfm(String afm);
    List<Repair> findByVehicleID(Long vehicleID);
    List<Repair> findByRepairID(Long repairID);
}
