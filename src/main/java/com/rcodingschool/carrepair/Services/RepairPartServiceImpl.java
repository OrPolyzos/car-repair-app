package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairPart;
import com.rcodingschool.carrepair.Repositories.RepairPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RepairPartServiceImpl implements RepairPartService {

    @Autowired
    private RepairPartRepository repairPartRepository;


    @Override
    public List<RepairPart> findAll() {
        return repairPartRepository.findAll();
    }

    @Override
    public List<RepairPart> findAllByRepairID(Long repairID) {
        return repairPartRepository.findAllByRepairID(repairID);
    }

    @Override
    public void save(RepairPart repairPart) {
        repairPartRepository.save(repairPart);
    }

    @Override
    public void deleteByRepairIDAndPartID(Long repairID,Long partID) {
        repairPartRepository.deleteByRepairIDAndPartID(repairID,partID);
    }


}
