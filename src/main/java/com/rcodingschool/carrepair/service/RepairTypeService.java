package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.RepairType;

public interface RepairTypeService {

    RepairType findByRepairTypeID( Short repairTypeID);
}
