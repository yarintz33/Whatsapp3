package com.example.whatsapp3.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.PostContact;
import com.example.whatsapp3.R;
import com.example.whatsapp3.myApplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostContactApi {

    Retrofit retrofit;
    ServerContactApi serverContactApi;

    public PostContactApi() {
//  this.postListData = postListData;
//  this.dao = dao;

        retrofit = new Retrofit.Builder()
                .baseUrl(myApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverContactApi = retrofit.create(ServerContactApi.class);
    }
    public void add(MutableLiveData<List<PostContact>> contactsListData, PostContact newContact){
        post(newContact);
        List<PostContact> contacts=  contactsListData.getValue();
        contacts.add(newContact);
        contactsListData.postValue(contacts);
    }

    public void get(MutableLiveData<List<PostContact>> contactsListData) {
        Call<List<PostContact>> call = serverContactApi.getPosts();
        call.enqueue(new Callback<List<PostContact>>() {
            @Override
            public void onFailure(Call<List<PostContact>> call, Throwable t) {
                }

            @Override
            public void onResponse(Call<List<PostContact>> call, Response<List<PostContact>> response) {
               contactsListData.setValue(response.body());
               // List<PostContact> posts = response.body();

            }
        });
    }
    public void post(PostContact contact){
       Call<PostContact> call = serverContactApi.createContact(contact);
        call.enqueue(new Callback<PostContact>() {
            @Override
            public void onResponse(Call<PostContact> call, Response<PostContact> response) {

            }

            @Override
            public void onFailure(Call<PostContact> call, Throwable t) {

            }
        });
    }

}
