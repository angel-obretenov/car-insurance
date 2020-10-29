package com.safety.car.models.dto.rest;

public class SearchPoliciesDto {

    Integer id;
    Integer isApproved;

    public SearchPoliciesDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }
}
