package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.Model;

import java.util.List;

public interface ModelRepository {

    Model getById(int id);

    List<Model> getAll();

}
