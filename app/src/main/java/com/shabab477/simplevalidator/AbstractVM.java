package com.shabab477.simplevalidator;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractVM extends BaseObservable {
    private Map<String, String> errorMap = new HashMap<>();

    // This class can be overridden to validate the form
    protected void validateViewModel(View view){}

    @Bindable
    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    @Bindable
    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
        notifyPropertyChanged(BR.errorMap);
    }
}
