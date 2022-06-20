package com.example.whatsapp3;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService2 extends FirebaseMessagingService {

    public static MessagesViewModel messages;

    public MyService2(){
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {

        messages.add(new Message(message.getData().get("content")),message.getData().get("from"), false);
        int a = 1;
    }

        //messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        //messagesViewModel.setContatId(username);

}