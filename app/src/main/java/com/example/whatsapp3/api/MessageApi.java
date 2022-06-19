package com.example.whatsapp3.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.Message;
import com.example.whatsapp3.R;
import com.example.whatsapp3.myApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageApi {

    Retrofit retrofit;
    ServerMessageApi serverMessageApi;

    public MessageApi() {
//  this.postListData = postListData;
//  this.dao = dao;

        retrofit = new Retrofit.Builder()
                .baseUrl(myApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverMessageApi = retrofit.create(ServerMessageApi.class);
    }
    /*public void add(MutableLiveData<List<User>> contactsListData, User newUser){
        List<PostContact> contacts=  contactsListData.getValue();
        contacts.add(newContact);
        contactsListData.postValue(contacts);*/
    //}

    public void get(MutableLiveData<List<Message>> messages, String contactId) {
        Call<List<Message>> call = serverMessageApi.getMessages(contactId);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                messages.setValue(null);
            }

            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                messages.setValue(response.body());
                // List<PostContact> posts = response.body();

            }
        });
    }
    public void add(MutableLiveData<List<Message>> messages, Message message, String contactId){
        List<Message> messagesList=  messages.getValue();
        messagesList.add(message);
        messages.postValue(messagesList);
        Call<Message> call = serverMessageApi.createMessage(message, contactId);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {


            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

}
