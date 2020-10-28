package com.safety.car.services.interfaces;

import com.safety.car.models.entity.PolicyRequest;

import java.util.List;

public interface PolicyRequestService {

    List<PolicyRequest> getAll();

    List<PolicyRequest> getAllPending();

    PolicyRequest getById(int id);

    void create(PolicyRequest policyRequest);

    void update(PolicyRequest policyToUpdate);
}