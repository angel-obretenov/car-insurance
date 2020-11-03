package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.Address;

import java.util.List;

public interface AddressRepository {

    List<Address> getAll();

    Address findByName(String addressName);

    Address getById(int id);

    boolean isNotSaved(String addressName);

    void createAddress(Address address);
}
