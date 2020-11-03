package com.safety.car.controllers.mvc;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.services.interfaces.EmailService;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.utils.mappers.interfaces.PolicyRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy-approval")
public class PolicyApprovalController {

    private final PolicyRequestService policyRequestService;
    private final PolicyRequestMapper policyRequestMapper;
    private final EmailService emailService;

    @Autowired
    public PolicyApprovalController(PolicyRequestService policyRequestService,
                                    PolicyRequestMapper policyRequestMapper, EmailService emailService) {
        this.policyRequestService = policyRequestService;
        this.policyRequestMapper = policyRequestMapper;
        this.emailService = emailService;
    }

    @GetMapping
    public String showPolicyApprovalPage(Model model) {

        model.addAttribute("policies", policyRequestService.getAllPending());

        return "policy-approval";
    }

    @PostMapping
    public String postApproval(String action) {

        try {
            PolicyRequest policyToApprove = policyRequestMapper.getUpdateFrom(action);
            emailService.sendPolicyStatusEmail(policyToApprove);
            policyRequestService.update(policyToApprove);
        } catch (NotFoundException e) {
            return "error";
        }

        return "redirect:/policy-approval";
    }
}