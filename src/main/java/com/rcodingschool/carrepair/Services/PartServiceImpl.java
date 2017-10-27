package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Part;
import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Domain.RepairPart;
import com.rcodingschool.carrepair.Repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private RepairPartService repairPartService;

    @Autowired
    private RepairService repairService;

    @Override
    public Part findOne(Long partID) { return partRepository.findOne(partID); }

    @Override
    public List<Part> findAll() {
        return partRepository.findAll();
    }

    @Override
    public List<Part> findByPartID(Long partID) {
        return partRepository.findByPartID(partID);
    }

    @Override
    public List<Part> findAllByPartPriceBetween(Long partPriceStart, Long partPriceEnd) {
        return partRepository.findAllByPartPriceBetween(partPriceStart, partPriceEnd);
    }

    @Override
    public void save(Part part) {
        partRepository.save(part);
        List<RepairPart> repairParts = repairPartService.findAllByPartID(part.getPartID());
        for (RepairPart repairPart : repairParts){
            repairService.save(repairService.findByRepairID(repairPart.getRepairID()).get(0));
        }

    }

    @Override
    public void deleteByPartID(Long partID) {
        List<RepairPart> repairParts = repairPartService.findAllByPartID(partID);
        for (RepairPart repairPart : repairParts){
            Repair repair = repairService.findByRepairID(repairPart.getRepairID()).get(0);
            repair.setRepairTotalCost(repair.getRepairTotalCost() - (repairPart.getQuantity() * partRepository.findByPartID(partID).get(0).getPartPrice()));
            repairService.saveAfterDeletedPart(repair);
        }
        partRepository.deleteByPartID(partID);
    }



}


