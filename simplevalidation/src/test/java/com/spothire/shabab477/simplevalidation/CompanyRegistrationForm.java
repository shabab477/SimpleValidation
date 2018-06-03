package com.spothire.shabab477.simplevalidation;


import com.spothire.shabab477.simplevalidation.annotation.NotNull;
import com.spothire.shabab477.simplevalidation.annotation.Size;

import java.io.Serializable;


public class CompanyRegistrationForm extends RegistrationForm implements Serializable {

    private final String role = "company";

    @NotNull
    @Size(min = 4, max = 20)
    private String industry;

    @NotNull
    @Size(min = 4, max = 20)
    private String companyName;

    public CompanyRegistrationForm(){}

    public String getRole() {
        return role;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void setDateOfBirth(String dateOfBirth) {
        super.setDateOfBirth(dateOfBirth);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setLocation(String location) {
        super.setLocation(location);
    }
}
