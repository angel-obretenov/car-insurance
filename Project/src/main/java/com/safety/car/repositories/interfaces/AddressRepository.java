package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.Address;

import java.util.List;

public interface AddressRepository {

    List<Address> getAll();

    Address findByName(String addressName);

    void createAddress(Address address);

    void updateAddress(Address address);

    void checkIfNeedsToBeSaved(String addressName);
}
