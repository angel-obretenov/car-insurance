package com.safety.car.services;

import com.safety.car.exceptions.AgeException;
import com.safety.car.models.entity.Car;
import com.safety.car.repositories.interfaces.CarRepository;
import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import com.safety.car.services.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.safety.car.utils.constants.service.ServiceConstants.DRIVER_AGE_ERROR;
import static java.lang.String.format;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final MulticriteriaRepository multicriteriaRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, MulticriteriaRepository multicriteriaRepository) {
        this.carRepository = carRepository;
        this.multicriteriaRepository = multicriteriaRepository;
    }

    @Override
    public Car getById(int id) {
        return carRepository.getById(id);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.getAll();
    }

    @Override
    public Double getTotalPremiumByCarId(int id) {

        return calculatePremium(id);
    }


    @Override
    public Double simulateOffer(Car car) {
        int id = carRepository.simulateOffer(car);
        return getTotalPremiumByCarId(id);
    }

    @Override
    public void create(Car car) {
        isAgeValid(car);

        carRepository.create(car);
    }

    @Override
    public void update(Car car) {
        isAgeValid(car);

        carRepository.update(car);
    }

    private void isAgeValid(Car car) {
        int driversAge = car.getDriversAge();
        if (driversAge < 18 || driversAge > 65) {
            throw new AgeException(format(DRIVER_AGE_ERROR, driversAge));
        }
    }

    private double calculatePremium(int id) {
        double totalPremium = 0;
        Car car = carRepository.getById(id);
        totalPremium = totalPremium + multicriteriaRepository.getByCCAndAge(car.getCubicCapacity(), car.getDriversAge());

        //adding 20% if accidents = true
        if (car.isHasAccidents()) totalPremium = totalPremium + totalPremium * 0.2;

        //adding 5% if drivers age below 25
        if (car.getDriversAge() < 25) {
            totalPremium = totalPremium + totalPremium * 0.05;
        }

        // adding tax
        totalPremium = totalPremium + totalPremium * 0.1;

        return totalPremium;
    }
}