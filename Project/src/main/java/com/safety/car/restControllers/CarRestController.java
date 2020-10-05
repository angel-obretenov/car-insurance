package com.safety.car.restControllers;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.Car;
import com.safety.car.services.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/{id}/total")
    public Double getTotalPremiumByCarId(@PathVariable int id) {
        try {
            return carService.getTotalPremiumByCarId(id);
        } catch (NotFoundException e) {
            return -1.1;
        }
    }

}
