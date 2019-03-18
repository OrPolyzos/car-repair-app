package com.rcodingschool.carrepair.repository;

import com.rcodingschool.carrepair.domain.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, Long> {

    List<Part> findAllByPartPriceBetween(Long partPriceStart, Long partPriceEnd);

}
