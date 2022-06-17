package com.example.whatsapp3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp3.repositories.PostContactsRepository;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    private PostContactsRepository repository;

    private LiveData<List<PostContact>> posts;

    public ContactsViewModel (){
        repository = new PostContactsRepository();
        //contacts = repository.getContacts();
        posts = repository.getAll();
    }

    public LiveData<List<PostContact>> get(){return posts;}
/*    public LiveData<List<PostContact>> get() {return  contacts;}

    public  void add(PostContact contact){ repository.add(contact);}*/
}
