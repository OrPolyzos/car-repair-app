package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairType;
import com.rcodingschool.carrepair.Repositories.RepairTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RepairTypeServiceImpl implements  RepairTypeService{

    @Autowired
    RepairTypeRepository repairTypeRepository;

    @Override
    public RepairType findByRepairTypeID( Short repairTypeID){
        return repairTypeRepository.findOne(repairTypeID);
    }
}
