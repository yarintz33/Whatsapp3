package com.example.whatsapp3.api;

import android.util.Log;

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

        List<PostContact> contacts=  contactsListData.getValue();
        if(! isThisIdInList(contacts, newContact.getId())){
            contacts.add(newContact);
            contactsListData.postValue(contacts);
            post(newContact);
        }
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
                Log.d("Info","respone (new contact)!");
            }

            @Override
            public void onFailure(Call<PostContact> call, Throwable t) {
                Log.d("Info","elready exist!");
            }
        });
    }

    public boolean isThisIdInList(List<PostContact> list, String id){
        for(int i=0; i< list.size(); i++){
            if(list.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }
}
