package com.shabab477.simplevalidator;



import android.util.Log;

import com.shabab477.simplevalidation.annotation.NotNull;
import com.shabab477.simplevalidation.annotation.Size;
import com.shabab477.simplevalidation.processor.ValidationProcessor;

public class LoginVM extends AbstractVM {

    @Size(min = 1)
    private String name;

    @NotNull
    @Size(size = 3, message = "The password needs to be 3 letters")
    private String password;

    @NotNull
    private String userCode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void validateViewModel() {
        setErrorMap(ValidationProcessor.validate(this));
    }
}
