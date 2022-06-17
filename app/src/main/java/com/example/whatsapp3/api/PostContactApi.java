package com.example.whatsapp3.api;

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
    ServerContactApi serverApi;

    public PostContactApi() {
//  this.postListData = postListData;
//  this.dao = dao;

        retrofit = new Retrofit.Builder()
                .baseUrl(myApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverApi = retrofit.create(ServerContactApi.class);
    }
    public void get() {
        Call<List<PostContact>> call = serverApi.getPosts();
        call.enqueue(new Callback<List<PostContact>>() {
            @Override
            public void onFailure(Call<List<PostContact>> call, Throwable t) {
            }

            @Override


            public void onResponse(Call<List<PostContact>> call, Response<List<PostContact>> response) {

                List<PostContact> posts = response.body();

            }
        });
    }
}
