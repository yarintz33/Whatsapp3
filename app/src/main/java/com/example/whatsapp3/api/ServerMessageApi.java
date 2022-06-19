package com.example.whatsapp3.api;

import com.example.whatsapp3.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerMessageApi {

    @GET("contacts/{contactId}/messages")
    Call<List<Message>> getMessages(@Path("contactId") String contactId);

    @GET("contacts/{contactId}/messages/{id}")
    Call<Message> getMessage(@Path("contactId") String contactId,@Path("id") String id);

    @POST("contacts/{contactId}/messages")
    Call<Message> createMessage(@Body Message message, @Path("contactId") String contactId);

    @DELETE("contacts/{contactId}/messages/{id}")
    Call<Void> deleteMessage(@Path("contactId") String contactId,@Path("id") String id);
}
