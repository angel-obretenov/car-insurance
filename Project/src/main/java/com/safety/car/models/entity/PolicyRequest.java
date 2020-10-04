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
    private PolicyDetails policyId;

    @ManyToOne
    @JoinColumn(name = "user_details_id")
    private UserDetails userDetailsId;

    @Column(name = "is_approved")
    private boolean isApproved;

    public PolicyRequest() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PolicyDetails getPolicyId() {
        return policyId;
    }

    public void setPolicyId(PolicyDetails policyId) {
        this.policyId = policyId;
    }

    public UserDetails getUserDetailsId() {
        return userDetailsId;
    }

    public void setUserDetailsId(UserDetails userDetailsId) {
        this.userDetailsId = userDetailsId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}