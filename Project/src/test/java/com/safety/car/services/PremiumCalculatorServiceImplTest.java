package com.safety.car.services;

import com.safety.car.models.entity.Brand;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.Model;
import com.safety.car.models.entity.PremiumValues;
import com.safety.car.repositories.interfaces.CarRepository;
import com.safety.car.repositories.interfaces.MulticriteriaRepository;
import com.safety.car.repositories.interfaces.PremiumRepository;
import com.safety.car.services.interfaces.PremiumService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PremiumCalculatorServiceImplTest {

    @Mock
    PremiumRepository premiumRepository;

    @Mock
    CarRepository carRepository;

    @Mock
    MulticriteriaRepository multicriteriaRepository;

    @InjectMocks
    PremiumServiceImpl premiumService;

    @InjectMocks
    PremiumCalculatorServiceImpl calcService;

    Car car = new Car();
    PremiumValues premiumValues = new PremiumValues();

    {
        premiumValues.setId(1);
        premiumValues.setDriverMinAge(18);
        premiumValues.setDriverMaxAge(65);
        premiumValues.setDriverAgeCoef(0.05);
        premiumValues.setAgeBelowForTax(25);
        premiumValues.setAccidentCoef(0.2);

        Brand brand = new Brand(1, "BMW");
        Model model = new Model(1, 2018, brand, "7 Series");
        car.setId(1);
        car.setBrand(brand);
        car.setModel(model);
        car.setCubicCapacity(1200);
        car.setDriversAge(19);
        car.setHasAccidents(true);
        car.setRegistrationDate("2017 10 16");
        car.setActive(true);

    }

    @Test
    void calculatePremium() {
//        when(multicriteriaRepository.getByCCAndAge(car.getCubicCapacity(), car.getDriversAge()))
//                .thenReturn(505.5);
//
//        when(premiumService.getById(1))
//                .thenReturn(premiumValues);
//
//        when(carRepository.getById(1))
//                .thenReturn(car);
//
//        when(carRepository.getById(1))
//                .thenReturn(car);
//
//        verify(calcService.calculatePremium(1), times(1));
    }
}