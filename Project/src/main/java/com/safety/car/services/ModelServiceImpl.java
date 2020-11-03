package com.safety.car.services;

import com.safety.car.models.entity.Model;
import com.safety.car.repositories.interfaces.ModelRepository;
import com.safety.car.services.interfaces.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Model getById(int id) {
        return modelRepository.getById(id);
    }

    @Override
    public List<Model> getAll() {
        return modelRepository.getAll();
    }

    @Override
    public List<Model> getByBrandId(int id) {
        return modelRepository.getByBrandId(id);
    }
}