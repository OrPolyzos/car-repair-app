package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.RepairType;
import org.springframework.data.repository.CrudRepository;

public interface RepairTypeRepository extends CrudRepository<RepairType, Short> {

    RepairType findByRepairTypeID(Short repairTypeID);
}
