package com.safety.car.services;

import com.safety.car.exceptions.AgeException;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.PremiumValues;
import com.safety.car.repositories.interfaces.CarRepository;
import com.safety.car.services.interfaces.CarService;
import com.safety.car.services.interfaces.PremiumCalculatorService;
import com.safety.car.services.interfaces.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.safety.car.utils.constants.Constants.DRIVER_AGE_ERROR;
import static java.lang.String.format;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PremiumService premiumService;
    private final PremiumCalculatorService premiumCalculatorService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PremiumService premiumService, PremiumCalculatorService premiumCalculatorService) {
        this.carRepository = carRepository;
        this.premiumService = premiumService;
        this.premiumCalculatorService = premiumCalculatorService;
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
        return premiumCalculatorService.calculatePremium(id);
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

    public void isAgeValid(Car car) {
        PremiumValues premiumValues = premiumService.getById(1);

        int driversAge = car.getDriversAge();

        if (driversAge < premiumValues.getDriverMinAge() || driversAge > premiumValues.getDriverMaxAge()) {
            throw new AgeException(format(DRIVER_AGE_ERROR, driversAge));
        }
    }
}