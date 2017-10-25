package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.RepairPart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairPartRepository extends CrudRepository<RepairPart, Long> {



}
