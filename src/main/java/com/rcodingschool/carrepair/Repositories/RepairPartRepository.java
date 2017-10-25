package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.RepairPart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairPartRepository extends CrudRepository<RepairPart, Long> {

        List<RepairPart> findAll();
        List<RepairPart> findByRepairID(Long repairID);
        RepairPart save(RepairPart repairPart);
//        void deleteByRepairIdOrPartId(Long repairID,Long partID);

}
