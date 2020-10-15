package com.safety.car.services.interfaces;

import com.safety.car.models.entity.Model;

import java.util.List;

public interface ModelService {

    Model getById(int id);

    List<Model> getAll();

    List<Model> getByBrandId(int id);
}
