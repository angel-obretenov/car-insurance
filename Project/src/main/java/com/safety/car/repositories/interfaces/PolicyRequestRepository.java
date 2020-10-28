package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.PolicyRequest;

import java.util.List;

public interface PolicyRequestRepository {

    List<PolicyRequest> getAll();

    PolicyRequest getById(int id);

    void create(PolicyRequest policyRequest);

    void update(PolicyRequest policyToUpdate);
}