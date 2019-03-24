package com.rcodingschool.carrepair.service.resource;

import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.model.RepairSearchForm;
import com.rcodingschool.carrepair.repository.RepairRepository;
import com.rcodingschool.carrepair.service.RepairPartService;
import com.rcodingschool.carrepair.service.RepairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.web.initializr.base.exception.ResourceException;
import spring.web.initializr.base.service.ResourceService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RepairResourceService extends ResourceService<Repair, RepairSearchForm, Long> {

    private static final LocalDateTime MIN_DT = LocalDate.of(1900, 1, 1).atStartOfDay();
    private static final LocalDateTime MAX_DT = LocalDate.of(2100, 1, 1).atStartOfDay();

    private final RepairRepository repairRepository;

    @Autowired
    private RepairTypeService repairTypeService;

    @Autowired
    private RepairPartService repairPartService;

    @Autowired
    private PartResourceService partResourceService;

    @Autowired
    public RepairResourceService(RepairRepository repairRepository) {
        super(repairRepository);
        this.repairRepository = repairRepository;
    }

    public List<Repair> findAllByRepairDateTimeBetweenAndVehicleID(LocalDateTime localDateTimeStart, LocalDateTime localDatetimeEnd, String vehicleID) {
        return repairRepository.findAllByRepairDateTimeBetweenAndVehicleID(localDateTimeStart, localDatetimeEnd, vehicleID);
    }

    public List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {
        return repairRepository.findAllByRepairDateTimeBetween(localDateTimeStart, localDateTimeEnd);
    }

    public List<Repair> findTop10ByOrderByRepairDateTimeDesc() {
        return repairRepository.findTop10ByOrderByRepairDateTimeDesc();
    }

    @Override
    public Repair insert(Repair resource) throws ResourceException {
        determineCostPerServiceType(resource);
        return super.insert(resource);
    }

    @Override
    public Repair update(Repair resource) throws ResourceException {
        determineCostPerServiceType(resource);
        determineCostPerPartsIncluded(resource);
        return super.update(resource);
    }

    @Override
    public List<Repair> searchBy(RepairSearchForm repairSearchForm) {
        prepareRepairSearchForm(repairSearchForm);
        if (repairSearchForm.getRepairID() != null) {
            return mapOptionalToList(findOptional(repairSearchForm.getRepairID()));
        } else if (repairSearchForm.getRepairVehicleID() != null) {
            return findAllByRepairDateTimeBetweenAndVehicleID(repairSearchForm.getRepairDateTimeStart(), repairSearchForm.getRepairDateTimeEnd(), repairSearchForm.getRepairVehicleID());
        } else {
            return findAllByRepairDateTimeBetween(repairSearchForm.getRepairDateTimeStart(), repairSearchForm.getRepairDateTimeEnd());
        }
    }

    private void prepareRepairSearchForm(RepairSearchForm repairSearchForm) {
        if (repairSearchForm.getRepairDateTimeStart() == null) {
            repairSearchForm.setRepairDateTimeStart(MIN_DT);
        }
        if (repairSearchForm.getRepairDateTimeEnd() == null) {
            repairSearchForm.setRepairDateTimeEnd(MAX_DT);
        }
    }

    private void determineCostPerServiceType(Repair repair) {
        Long costForType = Long.valueOf(repairTypeService.findByRepairTypeID(repair.getRepairTypeID()).getFixedPrice());
        repair.setRepairTotalCost(costForType);
    }

    private void determineCostPerPartsIncluded(Repair repair) {
        repairPartService.findAllByRepairID(repair.getId()).forEach(repairPart ->
                partResourceService.findOptional(repairPart.getPartID()).ifPresent(part ->
                        repair.setRepairTotalCost(repair.getRepairTotalCost() + (part.getPartPrice() * repairPart.getQuantity()))));
    }


}
