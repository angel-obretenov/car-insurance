package com.safety.car.models.dto.rest;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserCreateDto {

    private String firstName;

    private String lastName;

    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    @NotEmpty(message = "Confirm password must not be empty")
    private String confirmPassword;

    private Integer phoneNumber;

    private String addressName;

    public UserCreateDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}