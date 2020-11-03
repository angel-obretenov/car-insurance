package com.safety.car.controllers.mvc;

import com.safety.car.models.dto.rest.CarDto;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.BrandService;
import com.safety.car.services.interfaces.CarService;
import com.safety.car.services.interfaces.ModelService;
import com.safety.car.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Objects;

import static com.safety.car.utils.constants.Constants.*;
import static com.safety.car.utils.mappers.Helper.carDtoToCar;

@Controller
@RequestMapping("/")
@SessionAttributes({"carDto", "car", "redirectFromService"})
public class IndexController {
    private final CarService carService;
    private final UserService userService;
    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public IndexController(CarService carService, UserService userService, BrandService brandService, ModelService modelService) {
        this.carService = carService;
        this.userService = userService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping
    public String getHomePage(Model model,
                              HttpSession session,
                              Principal principal) {

        try {
            UserDetails user = userService.getByEmail(principal.getName());
            model.addAttribute("principal", user.getLastName());
            model.addAttribute("redirectFromService", session.getAttribute("redirectFromService"));
        } catch (NullPointerException e) {
            model.addAttribute("principal", GUEST_USER);
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
                       String action,
                       HttpSession session
    ) {

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
            model.addAttribute("redirectFromService", null);
            session.removeAttribute("redirectFromService");
        }

        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("models", modelService.getAll());

        model.addAttribute("carDto", Objects.requireNonNullElseGet(carDto, CarDto::new));

        return "index";
    }
}