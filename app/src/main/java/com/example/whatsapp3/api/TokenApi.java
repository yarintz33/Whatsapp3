package com.example.whatsapp3.api;

import com.example.whatsapp3.R;
import com.example.whatsapp3.Settings;
import com.example.whatsapp3.Token;
import com.example.whatsapp3.myApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TokenApi {

    Retrofit retrofit;
    ServerTokenApi serverTokenApi;

    public TokenApi() {

        retrofit = new Retrofit.Builder()
//                .baseUrl(myApplication.context.getString(R.string.BaseUrl))
                .baseUrl(Settings.serverNum)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverTokenApi = retrofit.create(ServerTokenApi.class);
    }

        public void post(Token token){
            Call<Token> call = serverTokenApi.createToken(token);
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {

                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {

                }
            });
        }

}
