package com.safety.car.services.interfaces;

import com.safety.car.models.entity.Brand;
import com.safety.car.models.entity.Car;
import com.safety.car.repositories.interfaces.BrandRepository;
import com.safety.car.repositories.interfaces.ModelRepository;

import java.util.List;

public interface CarService {
    Car getById(int id);

    List<Car> getAll();

    Double getTotalPremiumByCarId(int id);

    Double simulateOffer(Car car);

    void create(Car car);

    void update(Car car);
}
