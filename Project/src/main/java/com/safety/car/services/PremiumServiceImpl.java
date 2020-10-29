package com.safety.car.services;

import com.safety.car.models.entity.PremiumValues;
import com.safety.car.repositories.interfaces.PremiumRepository;
import com.safety.car.services.interfaces.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremiumServiceImpl implements PremiumService {
    private final PremiumRepository premiumRepository;

    @Autowired
    public PremiumServiceImpl(PremiumRepository premiumRepository) {
        this.premiumRepository = premiumRepository;
    }

    @Override
    public PremiumValues getById(int id) {
        return premiumRepository.getById(id);
    }

    @Override
    public List<PremiumValues> getAll() {
        return premiumRepository.getAll();
    }

    @Override
    public void create(PremiumValues premiumValues) {
        premiumRepository.create(premiumValues);
    }

    @Override
    public void update(PremiumValues premiumValues) {
        premiumRepository.update(premiumValues);
    }
}
