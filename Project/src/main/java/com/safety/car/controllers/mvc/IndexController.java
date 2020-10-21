package com.safety.car.controllers.mvc;

import com.safety.car.models.dto.rest.CarDto;
import com.safety.car.models.entity.Address;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.UserCar;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.safety.car.utils.constants.Constants.ANONYMOUS_USER_CONTROLLER;
import static com.safety.car.utils.constants.Constants.USER_NOT_FOUND_CONTROLLER;
import static com.safety.car.utils.mappers.Helper.carDtoToCar;

@Controller
@RequestMapping("/")
@SessionAttributes({"carDto", "car"})
public class IndexController {
    private final CarService carService;
    private final PolicyDetailsService policyDetailsService;
    private final UserService userService;
    private final GenericUtilityService<Address> addressService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final UserCarService userCarService;

    @Autowired
    public IndexController(CarService carService, PolicyDetailsService policyDetailsService, UserService userService, GenericUtilityService<Address> addressService, BrandService brandService, ModelService modelService, UserCarService userCarService) {
        this.carService = carService;
        this.policyDetailsService = policyDetailsService;
        this.userService = userService;
        this.addressService = addressService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.userCarService = userCarService;
    }

    @GetMapping
    public String getHomePage(Model model,
                              Principal principal,
                              HttpServletRequest request) {

        request.getSession();
        try {
            UserDetails user = userService.getByEmail(principal.getName());
            model.addAttribute("principal", user.getLastName());
        } catch (NullPointerException e) {
            model.addAttribute("principal", ANONYMOUS_USER_CONTROLLER);
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", USER_NOT_FOUND_CONTROLLER);
        }

//        UserCar userCar = userCarService.getByUserId(9);
//        System.out.println("Car: " + userCar.getCarId().getId());
//        System.out.println("User: " + userCar.getUserId().getId());

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
            UserDetails user = userService.getByEmail(principal.getName());
            model.addAttribute("principal", user.getLastName());
        } catch (NullPointerException e) {
            model.addAttribute("principal", ANONYMOUS_USER_CONTROLLER);
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", USER_NOT_FOUND_CONTROLLER);
        }

        if (action.equals("simulate")) {
            Car car = carDtoToCar(carDto, modelService, brandService);
            model.addAttribute("estimatedPrice", carService.simulateOffer(car));
            model.addAttribute("car", car);
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