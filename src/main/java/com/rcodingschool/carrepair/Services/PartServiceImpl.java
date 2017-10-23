package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Part;
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


    @Override
    public Part findOne(String partID) { return partRepository.findOne(partID); }

    @Override
    public List<Part> findAll() {
        return partRepository.findAll();
    }

    @Override
    public List<Part> findByPartID(String partID) {
        return partRepository.findByPartID(partID);
    }

    @Override
    public List<Part> findByPartPrice1PartPrice2(String partPrice1, String partPrice2) {
        return partRepository.findByPartPrice1PartPrice2(partPrice1, partPrice2);
    }

    @Override
    public void save(Part part) {
        partRepository.save(part);
    }

    @Override
    public void deleteByPartID(String partID) {
        partRepository.deleteByPartID(partID);
    }



}


