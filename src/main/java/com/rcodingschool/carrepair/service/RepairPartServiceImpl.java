package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.repository.RepairPartRepository;
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
    public List<RepairPart> findAllByPartID(Long partID) {
        return repairPartRepository.findAllByPartID(partID);
    }

    @Override
    public void save(RepairPart repairPart) throws ResourceNotFoundException, RepairNotFoundException {
        repairPartRepository.save(repairPart);
        Repair repair = repairService.findByRepairID(repairPart.getRepairID());
        repairService.save(repair);
    }

    @Override
    public void deleteByRepairIDAndPartID(Long repairID, Long partID) throws ResourceNotFoundException, RepairNotFoundException {
        repairPartRepository.deleteByRepairIDAndPartID(repairID, partID);
        Repair repair = repairService.findByRepairID(repairID);
        repairService.save(repair);
    }


}
