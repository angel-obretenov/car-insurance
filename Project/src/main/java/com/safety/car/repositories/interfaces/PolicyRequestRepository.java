package com.safety.car.repositories.interfaces;

import com.safety.car.models.entity.PolicyRequest;

import java.util.List;
import java.util.Optional;

public interface PolicyRequestRepository {

    List<PolicyRequest> getAll();

    List<PolicyRequest> getAllPending();

    List<PolicyRequest> getUserPolicies(int userId);

    PolicyRequest getById(int id);

    void create(PolicyRequest policyRequest);

    void update(PolicyRequest policyToUpdate);

    List<PolicyRequest> search(Optional<Integer> id, Optional<Integer> isApproved);
}