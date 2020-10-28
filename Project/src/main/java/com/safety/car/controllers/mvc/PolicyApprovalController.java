package com.safety.car.controllers.mvc;

import com.safety.car.models.dto.rest.PolicyRequestApprovalDto;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.utils.mappers.interfaces.PolicyRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/policy-approval")
public class PolicyApprovalController {

    private final PolicyRequestService policyRequestService;
    private final PolicyRequestMapper policyRequestMapper;

    @Autowired
    public PolicyApprovalController(PolicyRequestService policyRequestService,
                                    PolicyRequestMapper policyRequestMapper) {
        this.policyRequestService = policyRequestService;
        this.policyRequestMapper = policyRequestMapper;
    }

    @GetMapping
    public String showGalleryPage(Model model) {

        model.addAttribute("policies", policyRequestService.getAll());
//        model.addAttribute("approval", new PolicyRequestApprovalDto());
        return "policy-approval";
    }

    @GetMapping("/approve")
    public String postApproval(@RequestParam int id,
                               @RequestParam int bool,
                               PolicyRequestApprovalDto dto,
                               Principal principal) {

        dto.setApproved(bool == 1);
        dto.setId(id);
        PolicyRequest policyToApprove = policyRequestMapper.fromDto(dto);

        policyRequestService.update(policyToApprove);

        return "redirect:/policy-approval";
    }
}