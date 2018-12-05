package com.rcodingschool.carrepair.service;

import com.rcodingschool.carrepair.domain.Part;

import java.util.List;

public interface PartService {

    Part findOne(Long partID);

    List<Part> findAll();

    List<Part> findByPartID(Long partID);

    List<Part> findAllByPartPriceBetween(Long partPriceStart, Long partPriceEnd);

    void save(Part part);

    void deleteByPartID(Long partID);
}
