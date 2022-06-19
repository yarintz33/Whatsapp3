package com.example.whatsapp3.api;

import com.example.whatsapp3.PostContact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerContactApi {
    @GET("contacts")
    Call<List<PostContact>> getPosts();

    @POST("contacts")
    //Call<Void> createContact(@Body PostContact contact);
    Call<PostContact> createContact(@Body PostContact contact);
    @DELETE("contacts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
