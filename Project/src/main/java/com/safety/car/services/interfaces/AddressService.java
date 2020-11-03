package com.safety.car.services.interfaces;

import com.safety.car.models.entity.Address;

public interface AddressService {

    void createIfNotExist(String addressName);

    boolean isNotSaved(String addressName);

    Address findByName(String addressName);
}