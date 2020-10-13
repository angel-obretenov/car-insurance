package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.Brand;

import java.util.List;

public interface BrandRepository {

    Brand getById(int id);

    List<Brand> getAll();
}
