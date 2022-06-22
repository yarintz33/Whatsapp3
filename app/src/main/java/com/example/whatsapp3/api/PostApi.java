package com.example.whatsapp3.api;

import com.example.whatsapp3.Post;
import com.example.whatsapp3.R;
import com.example.whatsapp3.Settings;
import com.example.whatsapp3.myApplication;


import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class PostApi {
 // private PostDao dao;
 Retrofit retrofit;
 ServerApi serverApi;

 public PostApi() {
//  this.postListData = postListData;
//  this.dao = dao;

  retrofit = new Retrofit.Builder()
//          .baseUrl(myApplication.context.getString(R.string.BaseUrl))
          .baseUrl(Settings.serverNum)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
  serverApi = retrofit.create(ServerApi.class);
 }

 public void get() {
  Call<List<Post>> call = serverApi.getPosts();
  call.enqueue(new Callback<List<Post>>() {
   @Override
   public void onFailure(Call<List<Post>> call, Throwable t) {
   }

      @Override


      public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

            List<Post> posts = response.body();

      }
  });
 }
}