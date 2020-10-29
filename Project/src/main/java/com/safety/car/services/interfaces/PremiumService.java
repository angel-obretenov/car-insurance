package com.safety.car.services.interfaces;

import com.safety.car.models.entity.PremiumValues;

import java.util.List;

public interface PremiumService {

    PremiumValues getById(int id);

    List<PremiumValues> getAll();

    void create(PremiumValues premiumValues);

    void update(PremiumValues premiumValues);

}
