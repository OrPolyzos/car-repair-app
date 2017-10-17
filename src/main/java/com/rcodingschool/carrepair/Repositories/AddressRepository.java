package com.rcodingschool.carrepair.Repositories;

import com.rcodingschool.carrepair.Domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long>{

    Address save(Address address);
}
