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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, format("Car with id %d not found!", id));
        }
    }

    @GetMapping("/{id}/total")
    public Double getTotalPremiumByCarId(@PathVariable int id) {
        try {
            return carService.getTotalPremiumByCarId(id);
        } catch (NotFoundException e) {
            return -1.1;
        }
    }

    @PostMapping
    public String create(@RequestBody Car car){
        try{
            carService.create(car);
            return format("Car with brand %s created!", car.getBrand());
        } catch (AgeException e){
            return e.getMessage();
        }
    }

    @PutMapping
    public String update(@RequestBody Car car){
        try{
            carService.update(car);
            return format("Car with brand %s updated!", car.getBrand());
        } catch (AgeException e){
            return e.getMessage();
        }
    }

    @PostMapping("simulate")
    public String simulateOffer(@RequestBody Car car){
        try{
           return format("The estimated price is %.2f", carService.simulateOffer(car));
        } catch (AgeException e){
            return e.getMessage();
        }
    }
}