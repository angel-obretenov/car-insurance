package com.safety.car.services;

import com.safety.car.models.entity.Address;
import com.safety.car.repositories.interfaces.AddressRepository;
import com.safety.car.services.interfaces.AddressCombinedServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressCombinedServices {
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

    @Override
    public void createIfNotExist(String addressName) {
        if (isNotSaved(addressName)) {
            var address = new Address();
            address.setAddress(addressName);
            addressRepository.createAddress(address);
        }
    }

    @Override
    public boolean isNotSaved(String addressName) {
        return addressRepository.isNotSaved(addressName);
    }

    @Override
    public Address findByName(String addressName) {
        return addressRepository.findByName(addressName);
    }
}