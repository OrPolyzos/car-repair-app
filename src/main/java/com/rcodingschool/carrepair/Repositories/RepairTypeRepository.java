package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.RepairType;
import org.springframework.data.repository.CrudRepository;

public interface RepairTypeRepository extends CrudRepository<RepairType, Short> {

    RepairType findByRepairTypeID(Short repairTypeID);
}
