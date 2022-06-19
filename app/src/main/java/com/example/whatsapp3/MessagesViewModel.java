package com.example.whatsapp3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.whatsapp3.repositories.PostMessagesRepository;

import java.util.List;

public class MessagesViewModel extends ViewModel {

    private PostMessagesRepository repository;
    private String contactId;
    private LiveData<List<Message>> messages;

    public MessagesViewModel() {
//
//        repository = new PostMessagesRepository("Yarin");
//        messages = repository.getAll();
    }

    public void setContatId(String contactId){
        repository = new PostMessagesRepository(contactId);
        messages = repository.getAll();
    }
    // public LiveData<List<PostContact>> get(){return contacts;}
    public LiveData<List<Message>> get() {return  messages;}

    public  void add(Message message){ repository.add(message);}

}
