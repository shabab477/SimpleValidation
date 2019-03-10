package com.shabab477.simplevalidation;


import com.shabab477.simplevalidation.annotation.Email;
import com.shabab477.simplevalidation.annotation.NotNull;
import com.shabab477.simplevalidation.annotation.Size;

import java.io.Serializable;


public abstract class RegistrationForm implements Serializable {

    private String device = "mobile";

    @NotNull
    @Size(min = 4, max = 10)
    private String dateOfBirth;

    @NotNull
    @Size(min = 4, max = 10)
    private String phoneNumber = "";

    @NotNull
    @Email
    private String email = "";

    private boolean isPhoneNumberValid;

    @Size(min = 4, max = 20, message = "Password must be within ${min} and ${max} characters")
    private String password = "";

    private boolean isCompany;

    @NotNull
    @Size(min = 4, max = 30)
    private String location;

    public RegistrationForm(){}

    public RegistrationForm(
                            String location,
                            String dateOfBirth,
                            String phoneNumber,
                            String email,
                            String password) {
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public boolean isPhoneNumberValid() {
        return isPhoneNumberValid;
    }

    public void setPhoneNumberValid(boolean phoneNumberValid) {
        isPhoneNumberValid = phoneNumberValid;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
