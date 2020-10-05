package com.safety.car.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "multicriteria_range")
public class MulticriteriaTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cc_min")
    private Integer ccMin;

    @Column(name = "cc_max")
    private Integer ccMax;

    @Column(name = "car_age_min")
    private Integer carMinAge;

    @Column(name = "car_age_max")
    private Integer carMaxAge;

    @Column(name = "base_amount")
    private Double baseAmount;

    public Integer getId() {
        return id;
    }

    public Double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCcMin() {
        return ccMin;
    }

    public void setCcMin(Integer ccMin) {
        this.ccMin = ccMin;
    }

    public Integer getCcMax() {
        return ccMax;
    }

    public void setCcMax(Integer ccMax) {
        this.ccMax = ccMax;
    }

    public Integer getCarMinAge() {
        return carMinAge;
    }

    public void setCarMinAge(Integer carMinAge) {
        this.carMinAge = carMinAge;
    }

    public Integer getCarMaxAge() {
        return carMaxAge;
    }

    public void setCarMaxAge(Integer carMaxAge) {
        this.carMaxAge = carMaxAge;
    }
}