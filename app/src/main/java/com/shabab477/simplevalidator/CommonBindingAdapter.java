package com.shabab477.simplevalidator;

import android.widget.EditText;
import android.databinding.BindingAdapter;

public class CommonBindingAdapter {

    @BindingAdapter("android:errorText")
    public static void setErrorMessage(EditText view, String errorMessage) {
        if (errorMessage != null) {
            view.setError(errorMessage);
        } else {
            view.setError(null);
        }
    }
}
