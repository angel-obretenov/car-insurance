package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.PremiumValues;

import java.util.List;

public interface PremiumRepository {

    PremiumValues getById(int id);

    List<PremiumValues> getAll();

    void create(PremiumValues premiumValues);

    void update(PremiumValues premiumValues);
}
