package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Repair;
import com.rcodingschool.carrepair.Repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Override
    public Repair findOne(Long repairID) { return repairRepository.findOne(repairID); }

    @Override
    public List<Repair> findAll() { return repairRepository.findAll(); }

    @Override
    public List<Repair> findByRepairID(Long repairID) { return repairRepository.findByRepairId(repairID); }

    @Override
    public List<Repair> findByVehicleID(String vehicleID) { return repairRepository.findByVehicleId(vehicleID); }

    @Override
    public void save(Repair repair) { repairRepository.save(repair); }

    @Override
    public void deleteByRepairID(Long repairID) { repairRepository.deleteByRepairId(repairID);}
}
