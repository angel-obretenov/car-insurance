package com.safety.car.services;

import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.repositories.interfaces.PolicyRequestRepository;
import com.safety.car.services.interfaces.PolicyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<PolicyRequest> getAllPending() {
        return policyRequestRepository.getAllPending();
    }

    @Override
    public List<PolicyRequest> getUserPolicies(int userId) {
        return policyRequestRepository.getUserPolicies(userId);
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

    @Override
    public List<PolicyRequest> search(Optional<Integer> id, Optional<Integer> isApproved) {
        return policyRequestRepository.search(id, isApproved);
    }
}