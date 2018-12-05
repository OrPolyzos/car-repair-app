package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.RepairType;
import com.rcodingschool.carrepair.repository.RepairTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RepairTypeServiceImpl implements RepairTypeService {

    private final RepairTypeRepository repairTypeRepository;

    @Autowired
    public RepairTypeServiceImpl(RepairTypeRepository repairTypeRepository) {
        this.repairTypeRepository = repairTypeRepository;
    }

    @Override
    public RepairType findByRepairTypeID(Short repairTypeID) {
        return repairTypeRepository.findOne(repairTypeID);
    }
}
