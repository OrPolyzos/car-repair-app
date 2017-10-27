package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Repair;
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

    @Autowired
    private RepairService repairService;


    @Override
    public List<RepairPart> findAll() {
        return repairPartRepository.findAll();
    }

    @Override
    public List<RepairPart> findAllByRepairID(Long repairID) {
        return repairPartRepository.findAllByRepairID(repairID);
    }

    @Override
    public List<RepairPart> findAllByPartID(Long partID){
        return repairPartRepository.findAllByPartID(partID);
    }

    @Override
    public void save(RepairPart repairPart) {
        repairPartRepository.save(repairPart);
        Repair repair = repairService.findByRepairID(repairPart.getRepairID()).get(0);
        repairService.save(repair);
    }

    @Override
    public void deleteByRepairIDAndPartID(Long repairID,Long partID) {
        repairPartRepository.deleteByRepairIDAndPartID(repairID,partID);
        Repair repair = repairService.findByRepairID(repairID).get(0);
        repairService.save(repair);
    }


}
