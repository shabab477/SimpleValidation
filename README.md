# SimpleValidator
A lightweight library for common bean validations for Android.

This library is built keeping in mind of the common validation clauses that we need to go through while submitting data from `EditText`-s in Android. At this moment support for only a few clauses are given. They are:
* `@NotNull` : This annotation checks whether the field is null or not.
* `@Size` : This annotation works on `String` and `Collection` and checks for maximum and minimum sizes.
* `@Email` : Checks whether the String is an email address or not.
* `@Future`: Checks whether date is on the greater than the current date at any instance.

Using the **SimpleValidator** as the name says it is pretty simple. **SimpleValidator** is extremely powerful when using with [Data Binding](https://developer.android.com/topic/libraries/data-binding/) in Android.

We generally bind a data class with the `EditText`. A sample data class might be:

```
public class TestEmailClass {

    @Email
    private String email;

    @Future
    @NotNull
    private Date date;
    //Setters Getters and Constructor ignored for brevity
}
```

Now to validate an object of this class all you have to do is use the `Validator` class. An excerpt should be such as:

```
    Calendar instance = Calendar.getInstance();
    instance.add(Calendar.DATE, 1);
    Date date = instance.getTime();

    TestEmailClass testEmailClass = new TestEmailClass("shababkarim93@gmail.com", date);
    Map<String, String> validate = ValidationProcessor.validate(testEmailClass);
    //No error since both email and date(in the future) is valid.
    assertEquals(0, validate.size());

    testEmailClass.setEmail("asdasdasd");
    testEmailClass.setDate(new Date());
    validate = ValidationProcessor.validate(testEmailClass);
    //Two errors since both the fields are invalid
    assertEquals(2, validate.size());

    //There is a key value pair for the field in the Map.The key is the
    //name of the variable and the value is the error message
    assert validate.containsKey("email");
```
To add this library to your project just add this classpath to your root build.gradle
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

```

and to your project build.gradle file:

```
dependencies {
        implementation 'com.github.shabab477:SimpleValidation:v1.0.0'
}

```

```
MIT License

Copyright (c) 2018 Shabab Karim

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```