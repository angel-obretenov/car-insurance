package com.safety.car.controllers.mvc;

import com.safety.car.models.dto.rest.PolicyDetailsDto;
import com.safety.car.models.entity.Car;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.services.interfaces.BrandService;
import com.safety.car.services.interfaces.ModelService;
import com.safety.car.services.interfaces.PolicyDetailsService;
import com.safety.car.services.interfaces.UserService;
import com.safety.car.utils.mappers.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import static com.safety.car.utils.constants.Constants.FILL_FORM_POLICY;
import static com.safety.car.utils.mappers.Helper.pictureSaver;

@Controller
@RequestMapping("/service")
@SessionAttributes({"redirectFromService", "car"})
public class ServiceController {
    private final PolicyDetailsService policyDetailsService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final UserService userService;
    private final Helper helper;

    @Autowired
    public ServiceController(PolicyDetailsService policyDetailsService,
                             BrandService brandService,
                             ModelService modelService,
                             UserService userService,
                             Helper helper) {
        this.policyDetailsService = policyDetailsService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.userService = userService;
        this.helper = helper;
    }

    @GetMapping
    public String requestPolicy(Model model,
                                Principal principal,
                                HttpSession session
//                                @SessionAttribute("car") Car car
    ) {
        Car car = (Car) session.getAttribute("car");
        if (car != null) {
            model.addAttribute("car", car);
        } else {
            model.addAttribute("redirectFromService", FILL_FORM_POLICY);
            return "redirect:/";
        }
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
        if (bindingResult.hasErrors()) return "redirect:/";

        pictureSaver(dto);

        PolicyDetails policyDetails = helper.dtoToPolicyDetails(dto, car, userService.getByEmail(principal.getName()));
        policyDetailsService.create(policyDetails);

        return "redirect:/";
    }
}