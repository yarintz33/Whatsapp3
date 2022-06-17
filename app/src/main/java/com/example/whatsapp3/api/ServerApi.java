package com.example.whatsapp3.api;

import com.example.whatsapp3.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {
    @GET("Post")
    Call<List<Post>> getPosts();

    @POST("Post")
    Call<Void> createPost(@Body Post post);

    @DELETE("Post/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
