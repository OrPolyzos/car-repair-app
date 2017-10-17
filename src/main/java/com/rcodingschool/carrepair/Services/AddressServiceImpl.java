package com.rcodingschool.carrepair.Services;

import com.rcodingschool.carrepair.Domain.Address;
import com.rcodingschool.carrepair.Domain.User;
import com.rcodingschool.carrepair.Repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
