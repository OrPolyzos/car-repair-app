package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.RepairPart;
import com.rcodingschool.carrepair.Domain.RepairPartID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairPartRepository extends CrudRepository<RepairPart, RepairPartID> {

    List<RepairPart> findAll();

    List<RepairPart> findAllByPartID(Long partID);

    List<RepairPart> findAllByRepairID(Long repairID);

    RepairPart save(RepairPart repairPart);

    void deleteByRepairIDAndPartID(Long repairID, Long partID);



}
