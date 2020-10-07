package com.safety.car.services;

import com.safety.car.models.entity.Address;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.services.interfaces.GenericUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements GenericUtilityService<Address> {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.getAll();
    }

    @Override
    public Address getById(Integer id) {
        return addressRepository.getById(id);
    }
}
