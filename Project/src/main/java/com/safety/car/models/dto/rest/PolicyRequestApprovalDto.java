package com.safety.car.models.dto.rest;

public class PolicyRequestApprovalDto {

    private Integer id;

    private Boolean isApproved;

    public PolicyRequestApprovalDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}