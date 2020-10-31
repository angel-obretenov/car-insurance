package com.safety.car.services;

import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.repositories.interfaces.PolicyDetailsRepository;
import com.safety.car.services.interfaces.PolicyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyDetailsServiceImpl implements PolicyDetailsService {
    private final PolicyDetailsRepository policyDetailsRepository;

    @Autowired
    public PolicyDetailsServiceImpl(PolicyDetailsRepository policyDetailsRepository) {
        this.policyDetailsRepository = policyDetailsRepository;
    }

    @Override
    public List<PolicyDetails> getAll() {
        return policyDetailsRepository.getAll();
    }

    @Override
    public PolicyDetails getById(int id) {
        return policyDetailsRepository.getById(id);
    }

    @Override
    public void create(PolicyDetails policyDetails) {
        policyDetailsRepository.create(policyDetails);
    }

    @Override
    public void update(PolicyDetails policyDetails) {
        policyDetailsRepository.update(policyDetails);
    }
}