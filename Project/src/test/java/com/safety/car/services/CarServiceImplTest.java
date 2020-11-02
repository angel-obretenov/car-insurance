package com.safety.car.services;

import com.safety.car.models.entity.Brand;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.Model;
import com.safety.car.models.entity.PremiumValues;
import com.safety.car.repositories.interfaces.CarRepository;
import com.safety.car.repositories.interfaces.PremiumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    CarRepository mockRepo;

    @Mock
    PremiumRepository premiumRepo;

    @InjectMocks
    PremiumServiceImpl premiumService;

    @InjectMocks
    CarServiceImpl service;

    PremiumValues premiumValues = new PremiumValues();
    Car car = new Car();

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
    void getById() {
        when(mockRepo.getById(1)).thenReturn(car);

        Car test = service.getById(1);

        assertEquals(car, test);
    }

    @Test
    void getAll() {
        List<Car> list = new ArrayList<>();
        list.add(car);

        when(mockRepo.getAll()).thenReturn(list);

        List<Car> result = service.getAll();

        assertEquals(list, result);
    }

    @Test
    void create() {
        //TODO Can't mock inner   PremiumValues premiumValues = premiumService.getById(1); in isAgeValid();
        when(premiumRepo.getById(1))
                .thenReturn(premiumValues);

//        when(premiumService.getById(1))
//                .thenReturn(premiumValues);

//        service.create(car);
//
//        verify(mockRepo, times(1))
//                .create(car);
    }

    @Test
    void update() {

    }
}