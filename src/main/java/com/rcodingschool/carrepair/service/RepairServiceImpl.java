package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Repair;
import com.rcodingschool.carrepair.exception.base.ResourceNotFoundException;
import com.rcodingschool.carrepair.exception.repair.RepairNotFoundException;
import com.rcodingschool.carrepair.model.RepairSearchForm;
import com.rcodingschool.carrepair.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private VehicleResourceService vehicleResourceService;

    @Autowired
    private RepairTypeService repairTypeService;

    @Autowired
    private RepairPartService repairPartService;

    @Autowired
    private PartService partService;

    @Override
    public List<Repair> findAll() {
        return repairRepository.findAll();
    }


    @Override
    public List<Repair> findAllByRepairDateTimeBetweenAndVehicleID(LocalDateTime localDateTimeStart, LocalDateTime localDatetimeEnd, String vehicleID) {
        return repairRepository.findAllByRepairDateTimeBetweenAndVehicleID(localDateTimeStart, localDatetimeEnd, vehicleID);
    }

    @Override
    public List<Repair> findAllByRepairDateTimeBetween(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeEnd) {
        return repairRepository.findAllByRepairDateTimeBetween(localDateTimeStart, localDateTimeEnd);
    }

    @Override
    public List<Repair> findTop10ByOrderByRepairDateTimeDesc() {
        return repairRepository.findTop10ByOrderByRepairDateTimeDesc();
    }

    @Override
    public Repair findByRepairID(Long repairID) throws RepairNotFoundException {
        return repairRepository.findByRepairID(repairID).orElseThrow(() -> new RepairNotFoundException(repairID));
    }


    @Override
    public Repair findOne(Long repairID) {
        return repairRepository.findOne(repairID);
    }

    @Override
    public List<Repair> findByVehicleID(String vehicleID) {
        return repairRepository.findByVehicleID(vehicleID);
    }

    @Override
    public void save(Repair repair) throws ResourceNotFoundException {
        vehicleResourceService.findOrThrow(repair.getVehicleID());
        determineCostPerServiceType(repair);
        determineCostPerPartsIncluded(repair);
        repairRepository.save(repair);
    }

    private void determineCostPerPartsIncluded(Repair repair) {
        repairPartService.findAllByRepairID(repair.getRepairID()).forEach(repairPart ->
                partService.findByPartID(repairPart.getPartID()).ifPresent(part ->
                        repair.setRepairTotalCost(repair.getRepairTotalCost() + (part.getPartPrice() * repairPart.getQuantity()))));
    }

    private void determineCostPerServiceType(Repair repair) {
        Long costForType = Long.valueOf(repairTypeService.findByRepairTypeID(repair.getRepairTypeID()).getFixedPrice());
        repair.setRepairTotalCost(costForType);
    }

    @Override
    public void saveAfterDeletedPart(Repair repair) {
        repairRepository.save(repair);
    }

    @Override
    public void deleteByRepairID(Long repairID) throws RepairNotFoundException {
        findByRepairID(repairID);
        repairRepository.deleteByRepairID(repairID);
    }

    @Override
    public List<Repair> searchRepairs(RepairSearchForm repairSearchForm) {

        prepareRepairSearchForm(repairSearchForm);
        if (repairSearchForm.getRepairID() != null) {
            return repairRepository.findByRepairID(repairSearchForm.getRepairID()).map(Collections::singletonList).orElse(Collections.emptyList());
        } else if (repairSearchForm.getRepairVehicleID() != null) {
            return findAllByRepairDateTimeBetweenAndVehicleID(repairSearchForm.getRepairDateTimeStart(), repairSearchForm.getRepairDateTimeEnd(), repairSearchForm.getRepairVehicleID());
        } else {
            return findAllByRepairDateTimeBetween(repairSearchForm.getRepairDateTimeStart(), repairSearchForm.getRepairDateTimeEnd());
        }
    }

    private void prepareRepairSearchForm(RepairSearchForm repairSearchForm) {
        if (repairSearchForm.getRepairDateTimeEnd() == null) {
            repairSearchForm.setRepairDateTimeEnd(LocalDateTime.of(2100, 12, 31, 0, 0));
        }
        if (repairSearchForm.getRepairDateTimeStart() == null) {
            repairSearchForm.setRepairDateTimeStart(LocalDateTime.of(2000, 12, 31, 0, 0));
        }
    }
}
