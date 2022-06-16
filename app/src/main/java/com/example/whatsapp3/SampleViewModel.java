package com.example.whatsapp3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SampleViewModel extends ViewModel {

     private MutableLiveData<String> date;

    public MutableLiveData<String> getDate() {
        if (date == null) {
            date = new MutableLiveData<String>();
        }
         return date;
    }
}
