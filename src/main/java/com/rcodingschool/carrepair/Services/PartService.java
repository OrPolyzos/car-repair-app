package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Part;

import java.util.List;

public interface PartService {

    Part findOne(String partID);

    List<Part> findAll();

    List<Part> findByPartID(String partID);

    List<Part> findByPartPriceStartPartPriceEnd(String partPriceStart, String partPriceEnd);

    void save(Part part);

    void deleteByPartID(String partID);
}
