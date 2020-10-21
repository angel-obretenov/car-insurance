package com.safety.car.models.dto.rest;

import javax.validation.constraints.Positive;

public class PolicyApprovalDto {

    @Positive(message = "Must be positive")
    private Integer policyId;

    private String isApproved;

    public PolicyApprovalDto() {
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }
}