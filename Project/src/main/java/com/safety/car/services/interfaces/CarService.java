package com.safety.car.services.interfaces;

import com.safety.car.models.entity.Car;

import java.util.List;

public interface CarService {
    Car getById(int id);

    List<Car> getAll();

    Double getTotalPremiumByCarId(int id);

    Double simulateOffer(Car car);

    void create(Car car);

    void update(Car car);

    void isAgeValid(Car car);
}
