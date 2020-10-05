package com.safety.car.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "car_details")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "cubic_capacity")
    private Integer cubicCapacity;

    @Column(name = "registration_date")
    private String registrationDate;

    @Column(name = "driver_age")
    private Integer driversAge;

    @Column(name = "has_accidents")
    private boolean hasAccidents;

    @Column(name = "is_active")
    private boolean isActive;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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