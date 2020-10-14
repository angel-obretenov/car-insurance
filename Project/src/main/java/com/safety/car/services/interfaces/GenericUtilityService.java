package com.safety.car.services.interfaces;

import java.util.List;

public interface GenericUtilityService<T> {
    List<T> getAll();

    T getById(Integer id);

}
