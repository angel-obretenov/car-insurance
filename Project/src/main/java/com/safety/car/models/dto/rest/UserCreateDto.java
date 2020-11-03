package com.safety.car.models.dto.rest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserCreateDto {

    @NotEmpty(message = "First Name must not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name must not be empty")
    private String lastName;

    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @Size(min = 4, message = "Password must be bigger than 4 digits")
    private String password;

    @Size(min = 4, message = "Confirm Password must be bigger than 4 digits")
    private String confirmPassword;

    @Size(min = 10, max = 10, message = "Phone Number must be 10 digits")
    private String phoneNumber;

    @NotEmpty(message = "Address must not be empty")
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}