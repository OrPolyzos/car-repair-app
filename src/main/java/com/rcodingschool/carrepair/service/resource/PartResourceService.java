package com.rcodingschool.carrepair.service.resource;

import com.rcodingschool.carrepair.domain.Part;
import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.domain.RepairPart;
import com.rcodingschool.carrepair.model.PartSearchForm;
import com.rcodingschool.carrepair.repository.PartRepository;
import com.rcodingschool.carrepair.service.RepairPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.web.initializr.base.exception.ResourceException;
import spring.web.initializr.base.service.ResourceService;

import java.util.List;

@Component
public class PartResourceService extends ResourceService<Part, PartSearchForm, Long> {

    private PartRepository partRepository;
    private RepairResourceService repairResourceService;
    private RepairPartService repairPartService;

    @Autowired
    public PartResourceService(PartRepository partRepository, RepairResourceService repairResourceService, RepairPartService repairPartService) {
        super(partRepository);
        this.partRepository = partRepository;
        this.repairResourceService = repairResourceService;
        this.repairPartService = repairPartService;
    }

    @Override
    public Part update(Part resource) throws ResourceException {
        Part persistedPart = super.update(resource);
        updateRepairCosts(persistedPart);
        return persistedPart;
    }

    private void updateRepairCosts(Part part) throws ResourceException {
        List<RepairPart> repairParts = repairPartService.findAllByPartID(part.getId());
        for (RepairPart repairPart : repairParts) {
            repairResourceService.update(repairResourceService.find(repairPart.getRepairID()));
        }
    }

    @Override
    public void deleteById(Long partID) throws ResourceException {
        Part partToDelete = findOrThrow(partID);
        List<RepairPart> repairParts = repairPartService.findAllByPartID(partID);
        for (RepairPart repairPart : repairParts) {
            Repair repair = repairResourceService.findOrThrow(repairPart.getRepairID());
            repair.setRepairTotalCost(repair.getRepairTotalCost() - (repairPart.getQuantity() * partToDelete.getPartPrice()));
            repairResourceService.update(repair);
        }
        partRepository.delete(partID);
    }

    @Override
    public List<Part> searchBy(PartSearchForm partSearchForm) {
        if (partSearchForm.getPartPriceStart() == null) {
            partSearchForm.setPartPriceStart(Long.MIN_VALUE);
        }
        if (partSearchForm.getPartPriceEnd() == null) {
            partSearchForm.setPartPriceEnd(Long.MAX_VALUE);
        }

        if (partSearchForm.getPartID() != null) {
            return mapOptionalToList(findOptional(partSearchForm.getPartID()));
        } else {
            return partRepository.findAllByPartPriceBetween(partSearchForm.getPartPriceStart(), partSearchForm.getPartPriceEnd());
        }
    }
}
