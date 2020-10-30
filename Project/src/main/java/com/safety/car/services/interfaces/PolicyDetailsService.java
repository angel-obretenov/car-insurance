package com.safety.car.services.interfaces;

import com.safety.car.models.entity.PolicyDetails;

import java.util.List;

public interface PolicyDetailsService {

    List<PolicyDetails> getAll();

    List<PolicyDetails> getUserPolicies(int userId);

    PolicyDetails getById(int id);

    void create(PolicyDetails policyDetails);

    void update(PolicyDetails policyDetails);

}
