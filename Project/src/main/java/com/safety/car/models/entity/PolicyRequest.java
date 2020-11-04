package com.safety.car.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "policy_request")
public class PolicyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private PolicyDetails policyDetails;

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetails;

    @Column(name = "is_approved")
    private Boolean isApproved;

    public PolicyRequest() {
    }

    public PolicyRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PolicyDetails getPolicyDetails() {
        return policyDetails;
    }

    public void setPolicyDetails(PolicyDetails policyDetails) {
        this.policyDetails = policyDetails;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }
}