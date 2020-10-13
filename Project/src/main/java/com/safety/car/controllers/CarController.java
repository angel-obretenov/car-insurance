package com.safety.car.controllers;

import com.safety.car.models.dto.rest.CarDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.Car;
import com.safety.car.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static com.safety.car.utils.mappers.Helper.carDtoToCar;

@Controller
@RequestMapping("/index")
public class CarController {
    private final CarService carService;
    private final PolicyDetailsService policyDetailsService;
    private final GenericUtilityService<Address> addressService;
    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public CarController(CarService carService, PolicyDetailsService policyDetailsService, GenericUtilityService<Address> addressService, BrandService brandService, ModelService modelService) {
        this.carService = carService;
        this.policyDetailsService = policyDetailsService;
        this.addressService = addressService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping
    public String getHomePage(Model model,
                              Principal principal) {

        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("models", modelService.getAll());
        model.addAttribute("carDto", new CarDto());

        return "index";
    }

    @PostMapping
    public String post(@ModelAttribute("carDto") CarDto carDto,
                       Model model,
                       Principal principal,
                       BindingResult bindingResult,
                       String action) {

        if (bindingResult.hasErrors()) {
            return "index";
        }

        if (action.equals("simulate")) {
            Car car = carDtoToCar(carDto, modelService, brandService);
            model.addAttribute("estimatedPrice", carService.simulateOffer(car));
            double test = carService.simulateOffer(car);
            System.out.println(test);
        }

        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("models", modelService.getAll());
        model.addAttribute("car", new CarDto());

        return "index";
    }

}
