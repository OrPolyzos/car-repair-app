package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Part;
import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Domain.RepairPart;
import com.rcodingschool.carrepair.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairRepository repairRepository;

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
    public List<Repair> findAllByRepairDateTimeBetweenAndVehicleID(LocalDateTime localDateTimeStart, LocalDateTime localDatetimeEnd, String vehicleID){
        return repairRepository.findAllByRepairDateTimeBetweenAndVehicleID(localDateTimeStart,localDatetimeEnd, vehicleID);
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
    public List<Repair> findByRepairID(Long repairID) {
        return repairRepository.findByRepairID(repairID);
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
    public void save(Repair repair) {
        Long repairTotalCost = Long.valueOf(repairTypeService.findByRepairTypeID(repair.getRepairTypeID()).getFixedPrice());
        List<RepairPart> repairParts = repairPartService.findAllByRepairID(repair.getRepairID());
        for (RepairPart repairPart : repairParts){
            Part part = partService.findByPartID(repairPart.getPartID()).get(0);
            repairTotalCost += (part.getPartPrice() * repairPart.getQuantity());
        }
        repair.setRepairTotalCost(repairTotalCost);
        repairRepository.save(repair);
    }

    @Override
    public void saveAfterDeletedPart(Repair repair){
        repairRepository.save(repair);
    }

    @Override
    public void deleteByRepairID(Long repairID) {
        repairRepository.deleteByRepairID(repairID);
    }
}
