package com.safety.car.controllers.mvc;

import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.utils.mappers.interfaces.PolicyRequestMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class PolicyRequestController {

    private final PolicyRequestService policyRequestService;
    private final PolicyRequestMapper policyRequestMapper;

    public PolicyRequestController(PolicyRequestService policyRequestService, PolicyRequestMapper policyRequestMapper) {
        this.policyRequestService = policyRequestService;
        this.policyRequestMapper = policyRequestMapper;
    }

    @GetMapping
    public String getTickets(Model model) {
        model.addAttribute("tickets", policyRequestService.getAll());

        return "allTickets";
    }


}
