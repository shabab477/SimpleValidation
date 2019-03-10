package com.shabab477.simplevalidation;


import com.shabab477.simplevalidation.annotation.Size;

public class TestClass {

    @Size(min = 10, max = 100, message = "Name must be within ${min} and ${max} characters")
    private String name;

    public TestClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
