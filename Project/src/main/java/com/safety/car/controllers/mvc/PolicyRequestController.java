package com.safety.car.controllers.mvc;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.dto.rest.SearchPoliciesDto;
import com.safety.car.services.interfaces.PolicyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class PolicyRequestController {

    private final PolicyRequestService policyRequestService;

    @Autowired
    public PolicyRequestController(PolicyRequestService policyRequestService) {
        this.policyRequestService = policyRequestService;
    }

    @GetMapping
    public String getTickets(Model model) {
        model.addAttribute("tickets", policyRequestService.getAll());
        model.addAttribute("searchPoliciesDto", new SearchPoliciesDto());

        return "allTickets";
    }

    @PostMapping
    public String search(@ModelAttribute("searchPoliciesDto") SearchPoliciesDto dto, Model model) {
        try {
            model.addAttribute("tickets", policyRequestService.search(
                    Optional.ofNullable(dto.getId()),
                    Optional.ofNullable(dto.getIsApproved())));
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "allTickets";
    }
}