package com.safety.car.models.dto.rest;

import javax.validation.constraints.Positive;

public class PolicyRequestCreateDto {

    @Positive(message = "Must be positive")
    private Integer policyId;

    public PolicyRequestCreateDto() {
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }
}