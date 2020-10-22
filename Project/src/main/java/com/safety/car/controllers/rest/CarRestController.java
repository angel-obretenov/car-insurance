package com.safety.car.controllers.rest;

import com.safety.car.exceptions.AgeException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.Car;
import com.safety.car.services.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.safety.car.utils.constants.Constants.*;
import static java.lang.String.format;

@RestController
@RequestMapping("/api/car")
public class CarRestController {
    private final CarService carService;

    @Autowired
    public CarRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable int id){
        try {
            return carService.getById(id);
        } catch (NotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, format(CAR_ID_NOT_FOUND, id));
        }
    }

    @GetMapping("/{id}/total")
    public Double getTotalPremiumByCarId(@PathVariable int id) {
        try {
            return carService.getTotalPremiumByCarId(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, format(CAR_ID_NOT_FOUND, id));
        }
    }

    @PostMapping
    public String create(@RequestBody Car car){
        try{
            carService.create(car);
            return format(CAR_WITH_BRAND_CREATED, car.getBrand());
        } catch (AgeException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PutMapping
    public String update(@RequestBody Car car){
        try{
            carService.update(car);
            return format(CAR_WITH_BRAND_UPDATE, car.getBrand());
        } catch (AgeException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("simulate")
    public String simulateOffer(@RequestBody Car car){
        try{
           return format(ESTIMATED_PRICE_REST, carService.simulateOffer(car));
        } catch (AgeException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}