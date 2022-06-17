package com.example.whatsapp3;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService2 extends FirebaseMessagingService {
    public MyService2(){

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        int a = 1;
    }
}