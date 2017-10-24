package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, String>{

    Part findOne(String partID);

    List<Part> findAll();

    List<Part> findByPartID(String partID);

    List<Part> findByPartPriceStartPartPriceEnd(String partPriceStart, String partPriceEnd);

    Part save(Part part);

    void deleteByPartID(String partID);

}
