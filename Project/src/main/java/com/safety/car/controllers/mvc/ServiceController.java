package com.safety.car.controllers.mvc;

import com.safety.car.models.dto.rest.PolicyDetailsDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.services.interfaces.*;
import com.safety.car.utils.mappers.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.safety.car.utils.mappers.Helper.pictureSaver;

@Controller
@RequestMapping("/service")
@SessionAttributes({"carDto", "car"})
public class ServiceController {
    private final CarService carService;
    private final PolicyDetailsService policyDetailsService;
    private final GenericUtilityService<Address> addressService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final UserService userService;
    private final Helper helper;

    @Autowired
    public ServiceController(CarService carService,
                             PolicyDetailsService policyDetailsService,
                             GenericUtilityService<Address> addressService,
                             BrandService brandService,
                             ModelService modelService,
                             UserService userService, Helper helper) {
        this.carService = carService;
        this.policyDetailsService = policyDetailsService;
        this.addressService = addressService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.userService = userService;
        this.helper = helper;
    }

    @GetMapping
    public String requestPolicy(Model model,
                                Principal principal,
                                @SessionAttribute("car") Car car) {

        model.addAttribute("car", car);
        model.addAttribute("policy", new PolicyDetailsDto());
        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("models", modelService.getAll());
        model.addAttribute("user", userService.getByEmail(principal.getName()));

        return "service";
    }

    @PostMapping
    public String postPolicy(@ModelAttribute("policy") PolicyDetailsDto dto,
                             Model model,
                             Principal principal,
                             @SessionAttribute("car") Car car,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "index";

        pictureSaver(dto);

        PolicyDetails policyDetails = helper.dtoToPolicyDetails(dto, car, userService.getByEmail(principal.getName()));
        policyDetailsService.create(policyDetails);

        return "service";
    }

}