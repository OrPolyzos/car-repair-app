package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.RepairPart;
import com.rcodingschool.carrepair.Repositories.PartRepository;
import com.rcodingschool.carrepair.Repositories.RepairPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RepairPartServiceImpl implements RepairPartService{

    @Autowired
    private RepairPartRepository repairPartRepository;


    @Override
    public RepairPart findOne(Long repairID) { return repairPartRepository.findOne(repairID); }

    @Override
    public List<RepairPart> findAll() {
        return repairPartRepository.findAll();
    }

    @Override
    public List<RepairPart> findByRepairID(Long repairID) {
        return repairPartRepository.findByRepairID(repairID);
    }

    @Override
    public void save(RepairPart repairPart) {
        repairPartRepository.save(repairPart);
    }

    @Override
    public void deleteByRepairID(Long repairID) {
        repairPartRepository.deleteByRepairID(repairID);
    }


}
