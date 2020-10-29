package com.safety.car.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "premium_values")
public class PremiumValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tax_below_age")
    private Integer ageBelowForTax;

    @Column(name = "driver_min_age")
    private Integer driverMinAge;

    @Column(name = "driver_max_age")
    private Integer driverMaxAge;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "accident_coef")
    private Double accidentCoef;

    @Column(name = "driver_age_coef")
    private Double driverAgeCoef;

    public PremiumValues() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgeBelowForTax() {
        return ageBelowForTax;
    }

    public void setAgeBelowForTax(Integer driverMinAge) {
        this.ageBelowForTax = driverMinAge;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getAccidentCoef() {
        return accidentCoef;
    }

    public void setAccidentCoef(Double accidentCoef) {
        this.accidentCoef = accidentCoef;
    }

    public Double getDriverAgeCoef() {
        return driverAgeCoef;
    }

    public void setDriverAgeCoef(Double driverAgeCoef) {
        this.driverAgeCoef = driverAgeCoef;
    }

    public Integer getDriverMinAge() {
        return driverMinAge;
    }

    public void setDriverMinAge(Integer driverMinAge) {
        this.driverMinAge = driverMinAge;
    }

    public Integer getDriverMaxAge() {
        return driverMaxAge;
    }

    public void setDriverMaxAge(Integer driverMaxAge) {
        this.driverMaxAge = driverMaxAge;
    }
}
