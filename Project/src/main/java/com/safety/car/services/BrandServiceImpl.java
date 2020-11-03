package com.safety.car.services;

import com.safety.car.models.entity.Brand;
import com.safety.car.repositories.interfaces.BrandRepository;
import com.safety.car.services.interfaces.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand getById(int id) {
        return brandRepository.getById(id);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.getAll();
    }
}