package com.safety.car.controllers.mvc;

import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/my-policies")
public class MyPoliciesController {

    private final PolicyRequestService policyRequestService;
    private final UserService userService;

    @Autowired
    public MyPoliciesController(PolicyRequestService policyRequestService,
                                UserService userService) {
        this.policyRequestService = policyRequestService;
        this.userService = userService;
    }

    @GetMapping
    public String showAboutPage(Model model,
                                Principal principal) {

        UserDetails user = userService.getByEmail(principal.getName());

        model.addAttribute("userPolicies", policyRequestService.getUserPolicies(user.getId()));

        return "my-policies";
    }
}