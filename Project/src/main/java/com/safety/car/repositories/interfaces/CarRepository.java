package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.Car;

import java.util.List;

public interface CarRepository {

    Car getById(int id);

    List<Car> getAll();

    void create(Car car);

    void update(Car car);

    int simulateOffer(Car car);
}
