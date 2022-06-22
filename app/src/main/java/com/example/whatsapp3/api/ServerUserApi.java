package com.example.whatsapp3.api;

import com.example.whatsapp3.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServerUserApi {

        @GET("login/{username}/{password}")
        Call<User> getUser(@Path("username") String username, @Path("password") String password);

        @GET("registerCheck/{username}")
        Call<User> registerGetUser(@Path("username") String username);

        @POST("register")
        Call<User> createUser(@Body User user);

        @DELETE("Post/{id}")
        Call<Void> deletePost(@Path("id") int id);

        @PUT("signIn/{username}")
        Call<Void> signIn(@Path("username") String username);

}
