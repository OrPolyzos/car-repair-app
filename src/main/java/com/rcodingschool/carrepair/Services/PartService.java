package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Part;

import java.util.List;

public interface PartService {

    Part findOne(Long partID);

    List<Part> findAll();

    List<Part> findByPartID(Long partID);

    void save(Part part);

    void deleteByPartID(Long partID);
}
