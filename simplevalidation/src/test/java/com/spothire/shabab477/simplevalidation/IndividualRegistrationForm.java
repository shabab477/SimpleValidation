package com.spothire.shabab477.simplevalidation;


import java.io.Serializable;


public class IndividualRegistrationForm extends RegistrationForm implements Serializable {

    private String firstName = "";

    private String lastName = "";

    public IndividualRegistrationForm(){}

    @Override
    public void setDateOfBirth(String dateOfBirth) {
        super.setDateOfBirth(dateOfBirth);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
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
}
