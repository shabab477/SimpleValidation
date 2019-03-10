package com.shabab477.simplevalidation;


import com.shabab477.simplevalidation.processor.ValidationProcessor;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void annotationIsPresent(){
        ValidationProcessor.validate(new TestClass("Shabab"));
    }

    @Test
    public void checkMapSize(){
        Map<String, String> shabab = ValidationProcessor.validate(new TestClass("Shabab"));
        assertEquals(1, shabab.size());

        shabab = ValidationProcessor.validate(new TestClass("Shabab Karim"));
        assertEquals(0, shabab.size());
    }

    @Test
    public void checkMessage(){
        String expectedMessage = "Name must be within 10 and 100 characters";
        Map<String, String> shabab = ValidationProcessor.validate(new TestClass("Shabab"));
        assertEquals(expectedMessage, shabab.get("name"));
    }

    @Test
    public void checkEmail(){
        TestEmailClass testEmailClass = new TestEmailClass("shababkarim93@gmail.com", new Date());
        Map<String, String> validate = ValidationProcessor.validate(testEmailClass);

        assertEquals(1, validate.size());

        testEmailClass.setEmail("asdasdasd");
        validate = ValidationProcessor.validate(testEmailClass);
        assertEquals(2, validate.size());
    }

    @Test
    public void checkDate(){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 1);
        Date date = instance.getTime();

        TestEmailClass testEmailClass = new TestEmailClass("shababkarim93@gmail.com", date);
        Map<String, String> validate = ValidationProcessor.validate(testEmailClass);
        assertEquals(0, validate.size());

        testEmailClass.setEmail("asdasdasd");
        testEmailClass.setDate(new Date());
        validate = ValidationProcessor.validate(testEmailClass);
        assertEquals(2, validate.size());

        assert validate.containsKey("email");
    }

    @Test
    public void checkObject(){
        RegistrationForm form = new CompanyRegistrationForm();
        Map<String, String> validate = ValidationProcessor.validate(form);

        assertEquals(2, validate.size());
    }
}