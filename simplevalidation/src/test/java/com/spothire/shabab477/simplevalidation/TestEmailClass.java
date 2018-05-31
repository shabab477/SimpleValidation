package com.spothire.shabab477.simplevalidation;


import com.spothire.shabab477.simplevalidation.annotation.Email;
import com.spothire.shabab477.simplevalidation.annotation.Future;
import com.spothire.shabab477.simplevalidation.annotation.NotNull;

import java.util.Date;

public class TestEmailClass {

    @Email
    private String email;

    @Future
    @NotNull
    private Date date;

    public TestEmailClass(String email, Date date) {
        this.email = email;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
