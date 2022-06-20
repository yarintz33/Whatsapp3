package com.example.whatsapp3.api;

import com.example.whatsapp3.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServerTokenApi {



    @POST("token")
    Call<Token> createToken(@Body Token token);
}
