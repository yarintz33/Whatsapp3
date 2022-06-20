package com.example.whatsapp3.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.R;
import com.example.whatsapp3.User;
import com.example.whatsapp3.myApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {

    Retrofit retrofit;
    ServerUserApi serverUserApi;

    public UserApi() {
//  this.postListData = postListData;
//  this.dao = dao;

        retrofit = new Retrofit.Builder()
                .baseUrl(myApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverUserApi = retrofit.create(ServerUserApi.class);
    }
    /*public void add(MutableLiveData<List<User>> contactsListData, User newUser){
        List<PostContact> contacts=  contactsListData.getValue();
        contacts.add(newContact);
        contactsListData.postValue(contacts);*/
    //}

    public void signIn(String username){
        Call<Void> call = serverUserApi.signIn(username);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        } );
    }
    public void get(MutableLiveData<User> logedInUser, String username,String password) {
        Call<User> call = serverUserApi.getUser(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                logedInUser.setValue(null);
            }

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                logedInUser.setValue(response.body());
                // List<PostContact> posts = response.body();

            }
        });
    }
    public void post(User newUser){
        Call<User> call = serverUserApi.createUser(newUser);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
