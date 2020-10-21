package com.safety.car.controllers.mvc;

import com.safety.car.models.dto.rest.CarDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.Car;
import com.safety.car.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.safety.car.utils.mappers.Helper.carDtoToCar;

@Controller
@RequestMapping("/")
@SessionAttributes("carDto")
public class IndexController {
    private final CarService carService;
    private final PolicyDetailsService policyDetailsService;
    private final UserDetailsService userDetailsService;
    private final GenericUtilityService<Address> addressService;
    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public IndexController(CarService carService,
                           PolicyDetailsService policyDetailsService,
                           UserDetailsService userDetailsService,
                           GenericUtilityService<Address> addressService,
                           BrandService brandService,
                           ModelService modelService) {
        this.carService = carService;
        this.policyDetailsService = policyDetailsService;
        this.userDetailsService = userDetailsService;
        this.addressService = addressService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping
    public String getHomePage(Model model,
                              Principal principal) {

        try {
            model.addAttribute("principal", principal.getName());
        } catch (NullPointerException e) {
            model.addAttribute("principal", "Anonymous user");
        }
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

        try {
            model.addAttribute("principal", principal.getName());
        } catch (NullPointerException e) {
            model.addAttribute("principal", "Anonymous user");
        }

        if (action.equals("simulate")) {
            Car car = carDtoToCar(carDto, modelService, brandService);
            model.addAttribute("estimatedPrice", carService.simulateOffer(car));
        }

        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("models", modelService.getAll());

        if (!(carDto == null)) {
            model.addAttribute("carDto", carDto);
        } else {
            model.addAttribute("carDto", new CarDto());
        }

        return "index";
    }
}