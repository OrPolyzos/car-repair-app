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
    public void save(Part part) {
        partRepository.save(part);
    }

    @Override
    public void deleteByPartID(Long partID) {
        partRepository.deleteByPartID(partID);
    }



}


