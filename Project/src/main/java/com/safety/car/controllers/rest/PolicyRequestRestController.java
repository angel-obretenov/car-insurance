package com.safety.car.controllers.rest;

import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.dto.rest.PolicyApprovalDto;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.utils.mappers.interfaces.PolicyRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/policy-requests")
public class PolicyRequestRestController {

    private final PolicyRequestService policyRequestService;
    private final PolicyRequestMapper policyRequestMapper;

    @Autowired
    public PolicyRequestRestController(PolicyRequestService policyRequestService, PolicyRequestMapper policyRequestMapper) {
        this.policyRequestService = policyRequestService;
        this.policyRequestMapper = policyRequestMapper;
    }

    @GetMapping
    public List<PolicyRequest> getAll() {
        return policyRequestService.getAll();
    }

    @GetMapping("/{id}")
    public PolicyRequest getById(@PathVariable int id) {
        try {
            return policyRequestService.getById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }

    @PostMapping
    public PolicyRequest create(@RequestBody PolicyApprovalDto policyApprovalDto) {
        try {
            PolicyRequest updatePolicy = policyRequestMapper.fromDto(policyApprovalDto);
            policyRequestService.create(updatePolicy);
            return updatePolicy;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    e.getMessage());
        }
    }
}