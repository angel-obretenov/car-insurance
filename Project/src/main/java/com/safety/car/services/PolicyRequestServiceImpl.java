package com.safety.car.services;

import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.repositories.interfaces.PolicyRequestRepository;
import com.safety.car.services.interfaces.PolicyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyRequestServiceImpl implements PolicyRequestService {

    private final PolicyRequestRepository policyRequestRepository;

    @Autowired
    public PolicyRequestServiceImpl(PolicyRequestRepository policyRequestRepository) {
        this.policyRequestRepository = policyRequestRepository;
    }

    @Override

    public List<PolicyRequest> getAll() {
        return policyRequestRepository.getAll();
    }

    @Override
    public PolicyRequest getById(int id) {
        return policyRequestRepository.getById(id);
    }

    @Override
    public void create(PolicyRequest policyRequest) {
        policyRequestRepository.create(policyRequest);
    }

    @Override
    public void update(PolicyRequest policyToUpdate) {
        policyRequestRepository.update(policyToUpdate);
    }
}