package com.example.whatsapp3.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.R;
import com.example.whatsapp3.Settings;
import com.example.whatsapp3.User;
import com.example.whatsapp3.myApplication;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {
    public static String jwt_token = "";
    Retrofit retrofit;
    ServerUserApi serverUserApi;

    public UserApi() {
//  this.postListData = postListData;
//  this.dao = dao;

        retrofit = new Retrofit.Builder()
//                .baseUrl(myApplication.context.getString(R.string.BaseUrl))
                .baseUrl(Settings.serverNum)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverUserApi = retrofit.create(ServerUserApi.class);
    }

    /*public void add(MutableLiveData<List<User>> contactsListData, User newUser){
        List<PostContact> contacts=  contactsListData.getValue();
        contacts.add(newContact);
        contactsListData.postValue(contacts);*/
    //}

    public void signIn(MutableLiveData<User> logedInUser, String username, String password){
        Call<User> call = serverUserApi.signIn(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                jwt_token = response.body().getJwtToken();
                logedInUser.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Info","failure");
                logedInUser.setValue(null);
            }
        } );
    }
    public void get(MutableLiveData<User> logedInUser, String username,String password) {
        Call<User> call = serverUserApi.getUser(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Info","failure");
                logedInUser.setValue(null);
            }

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("Info","success");
                logedInUser.setValue(response.body());
                // List<PostContact> posts = response.body();

            }
        });
    }
    public void checkRegister(MutableLiveData<User> logedInUser, String username){
        Call<User> call = serverUserApi.registerGetUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                logedInUser.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

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
