package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.repository.RepairPartRepository;
import com.rcodingschool.carrepair.service.resource.RepairResourceService;
import ore.spring.web.initializr.exception.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RepairPartServiceImpl implements RepairPartService {

    private final RepairPartRepository repairPartRepository;
    private final RepairResourceService repairResourceService;

    @Autowired
    public RepairPartServiceImpl(RepairPartRepository repairPartRepository, RepairResourceService repairResourceService) {
        this.repairPartRepository = repairPartRepository;
        this.repairResourceService = repairResourceService;
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
    public void save(RepairPart repairPart) throws ResourceException {
        repairPartRepository.save(repairPart);
        Repair repair = repairResourceService.findOrThrow(repairPart.getRepairID());
        repairResourceService.update(repair);
    }

    @Override
    public void deleteByRepairIDAndPartID(Long repairID, Long partID) throws ResourceException {
        repairPartRepository.deleteByRepairIDAndPartID(repairID, partID);
        Repair repair = repairResourceService.findOrThrow(repairID);
        repairResourceService.update(repair);
    }


}
