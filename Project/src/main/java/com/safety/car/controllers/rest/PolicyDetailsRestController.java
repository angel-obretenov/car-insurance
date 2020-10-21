package com.safety.car.controllers.rest;

import com.safety.car.models.dto.rest.PolicyDetailsDto;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.services.interfaces.PolicyDetailsService;
import com.safety.car.utils.mappers.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyDetailsRestController {
    private final Helper policyHelper;
    private final PolicyDetailsService policyDetailsService;

    @Autowired
    public PolicyDetailsRestController(Helper policyHelper, PolicyDetailsService policyDetailsService) {
        this.policyHelper = policyHelper;
        this.policyDetailsService = policyDetailsService;
    }

    @GetMapping
    public List<PolicyDetails> getAll(){
        try {
            return policyDetailsService.getAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public PolicyDetails getById(@PathVariable int id){
        try {
            return policyDetailsService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public PolicyDetails create(@RequestBody PolicyDetailsDto dto){
        try {
           PolicyDetails policyDetails = policyHelper.dtoToPolicyDetails(dto);
            policyDetailsService.create(policyDetails);
            return policyDetails;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping
    public PolicyDetails update(@RequestBody PolicyDetails policyDetails){
        try {
            policyDetailsService.update(policyDetails);
            return policyDetails;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
