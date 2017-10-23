package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.Part;
import com.rcodingschool.carrepair.Domain.RepairPart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends CrudRepository<Part, Long>{

    Part findOne(Long partID);

    List<Part> findAll();

    List<Part> findByPartID(Long partID);

    Part save(Part user);

    void deleteByPartID(Long partID);

}
