package com.safety.car.models.dto.rest;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class CarDto {

    private Integer id;

    @PositiveOrZero
    private Integer brandId;

    @PositiveOrZero
    private Integer modelId;

    @PositiveOrZero
    private Integer cubicCapacity;

    private String registrationDate;

    @Size(min = 18, max = 65, message = "Driver should be between 18 and 65 to be able to drive")
    private Integer driversAge;

    private boolean hasAccidents;

    private boolean isActive;

    public Integer getId() {
        return id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getCubicCapacity() {
        return cubicCapacity;
    }

    public void setCubicCapacity(Integer cubicCapacity) {
        this.cubicCapacity = cubicCapacity;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getDriversAge() {
        return driversAge;
    }

    public void setDriversAge(Integer driversAge) {
        this.driversAge = driversAge;
    }

    public boolean isHasAccidents() {
        return hasAccidents;
    }

    public void setHasAccidents(boolean hasAccidents) {
        this.hasAccidents = hasAccidents;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
