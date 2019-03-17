package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import com.rcodingschool.carrepair.exception.part.PartNotFoundException;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.model.PartSearchForm;
import com.rcodingschool.carrepair.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Part findOne(Long partID) {
        return partRepository.findOne(partID);
    }

    @Override
    public List<Part> findAll() {
        return partRepository.findAll();
    }

    @Override
    public Optional<Part> findByPartID(Long partID) {
        return partRepository.findByPartID(partID);
    }

    @Override
    public List<Part> findAllByPartPriceBetween(Long partPriceStart, Long partPriceEnd) {
        return partRepository.findAllByPartPriceBetween(partPriceStart, partPriceEnd);
    }

    @Override
    public void save(Part part) throws RepairNotFoundException, ResourceNotFoundException {
        partRepository.save(part);
        updateRepairCosts(part);
    }

    private void updateRepairCosts(Part part) throws RepairNotFoundException, ResourceNotFoundException {
        List<RepairPart> repairParts = repairPartService.findAllByPartID(part.getPartID());
        for (RepairPart repairPart : repairParts) {
            repairService.save(repairService.findByRepairID(repairPart.getRepairID()));
        }
    }

    @Override
    public void deleteByPartID(Long partID) throws RepairNotFoundException, PartNotFoundException {
        List<RepairPart> repairParts = repairPartService.findAllByPartID(partID);
        for (RepairPart repairPart : repairParts) {
            Repair repair = repairService.findByRepairID(repairPart.getRepairID());
            repair.setRepairTotalCost(repair.getRepairTotalCost() - (repairPart.getQuantity() * partRepository.findByPartID(partID).orElseThrow(() -> new PartNotFoundException(partID)).getPartPrice()));
            repairService.saveAfterDeletedPart(repair);
        }
        partRepository.deleteByPartID(partID);
    }

    @Override
    public List<Part> searchForParts(PartSearchForm partSearchForm) {
        if (partSearchForm.getPartPriceStart() == null) {
            partSearchForm.setPartPriceStart(Long.MIN_VALUE);
        }
        if (partSearchForm.getPartPriceEnd() == null) {
            partSearchForm.setPartPriceEnd(Long.MAX_VALUE);
        }

        if (partSearchForm.getPartID() != null) {
            return findByPartID(partSearchForm.getPartID()).map(Collections::singletonList).orElse(Collections.emptyList());
        } else {
            return findAllByPartPriceBetween(partSearchForm.getPartPriceStart(), partSearchForm.getPartPriceEnd());
        }
    }
}


