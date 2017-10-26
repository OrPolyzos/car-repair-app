package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairType;

public interface RepairTypeService {

    RepairType findByRepairTypeID( Short repairTypeID);
}
