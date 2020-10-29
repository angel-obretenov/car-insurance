package com.safety.car.controllers.rest;

import com.safety.car.exceptions.EmptyException;
import com.safety.car.exceptions.NotFoundException;
import com.safety.car.models.dto.rest.PolicyDetailsDto;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.services.interfaces.PolicyDetailsService;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.utils.mappers.Helper;
import com.safety.car.utils.mappers.interfaces.PolicyRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyDetailsRestController {

    private final Helper policyHelper;
    private final PolicyRequestMapper policyRequestMapper;
    private final PolicyRequestService policyRequestService;
    private final PolicyDetailsService policyDetailsService;

    @Autowired
    public PolicyDetailsRestController(Helper policyHelper,
                                       PolicyRequestMapper policyRequestMapper,
                                       PolicyRequestService policyRequestService,
                                       PolicyDetailsService policyDetailsService) {
        this.policyHelper = policyHelper;
        this.policyRequestMapper = policyRequestMapper;
        this.policyRequestService = policyRequestService;
        this.policyDetailsService = policyDetailsService;
    }

    @GetMapping
    public List<PolicyDetails> getAll() {
        try {
            return policyDetailsService.getAll();
        } catch (EmptyException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PolicyDetails getById(@PathVariable int id) {
        try {
            return policyDetailsService.getById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public PolicyDetails create(@RequestBody PolicyDetailsDto dto) {
        try {
            PolicyDetails policyDetails = policyHelper.dtoToPolicyDetails(dto);
            policyDetailsService.create(policyDetails);

            PolicyRequest policyRequest = policyRequestMapper.from(policyDetails);
            policyRequestService.create(policyRequest);

            return policyDetails;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping
    public PolicyDetails update(@RequestBody PolicyDetails policyDetails) {
        try {
            policyDetailsService.update(policyDetails);
            return policyDetails;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}