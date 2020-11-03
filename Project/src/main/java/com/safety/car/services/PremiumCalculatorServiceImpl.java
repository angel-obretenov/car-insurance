package com.safety.car.services;

import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.PremiumValues;
import com.safety.car.repositories.interfaces.CarRepository;
import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import com.safety.car.services.interfaces.PremiumCalculatorService;
import com.safety.car.services.interfaces.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremiumCalculatorServiceImpl implements PremiumCalculatorService {
    private final PremiumService premiumService;
    private final CarRepository carRepository;
    private final MulticriteriaRepository multicriteriaRepository;

    @Autowired
    public PremiumCalculatorServiceImpl(PremiumService premiumService, CarRepository carRepository, MulticriteriaRepository multicriteriaRepository) {
        this.premiumService = premiumService;
        this.carRepository = carRepository;
        this.multicriteriaRepository = multicriteriaRepository;
    }

    @Override
    public double calculatePremium(int id) {
        PremiumValues premiumValues = premiumService.getById(1);
        double totalPremium = 0;
        Car car = carRepository.getById(id);
        totalPremium = totalPremium + multicriteriaRepository.getByCCAndAge(car.getCubicCapacity(), car.getDriversAge());

        //adding 20% if accidents = true
        if (car.isHasAccidents()) totalPremium = totalPremium + totalPremium * premiumValues.getAccidentCoef();

        //adding 5% if drivers age below 25
        if (car.getDriversAge() < premiumValues.getAgeBelowForTax()) {
            totalPremium = totalPremium + totalPremium * premiumValues.getDriverAgeCoef();
        }

        // adding tax
        totalPremium = totalPremium + totalPremium * premiumValues.getTax();

        return totalPremium;
    }
}