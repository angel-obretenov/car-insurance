package com.safety.car.services.interfaces;

import com.safety.car.models.entity.Brand;

import java.util.List;

public interface BrandService {

    Brand getById(int id);

    List<Brand> getAll();
}
