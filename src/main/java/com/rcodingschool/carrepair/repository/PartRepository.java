package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends CrudRepository<Part, Long> {

    Part findOne(Long partID);

    List<Part> findAll();

    Optional<Part> findByPartID(Long partID);

    List<Part> findAllByPartPriceBetween(Long partPriceStart, Long partPriceEnd);

    Part save(Part part);

    void deleteByPartID(Long partID);

}
