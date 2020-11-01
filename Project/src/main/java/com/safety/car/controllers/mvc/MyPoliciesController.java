package com.safety.car.controllers.mvc;

import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.PolicyDetailsService;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/my-policies")
public class MyPoliciesController {

    private final PolicyRequestService policyRequestService;
    private final PolicyDetailsService policyDetailsService;
    private final UserService userService;

    @Autowired
    public MyPoliciesController(PolicyRequestService policyRequestService,
                                PolicyDetailsService policyDetailsService,
                                UserService userService) {
        this.policyRequestService = policyRequestService;
        this.policyDetailsService = policyDetailsService;
        this.userService = userService;
    }

    @GetMapping
    public String showAboutPage(Model model,
                                Principal principal) {

        UserDetails user = userService.getByEmail(principal.getName());

        model.addAttribute("userPolicies", policyRequestService.getUserPolicies(user.getId()));

        return "my-policies";
    }

    @PostMapping
    public String deletePolicy(@RequestParam int id) {

        PolicyRequest policyToDelete = policyRequestService.getById(id);

        policyDetailsService.delete(policyToDelete.getPolicyDetails());

        return "redirect:my-policies";
    }
}