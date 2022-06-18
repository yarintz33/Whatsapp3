package com.example.whatsapp3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.whatsapp3.repositories.PostContactsRepository;

import java.util.List;

public class ContactsViewModel extends ViewModel {

    private PostContactsRepository repository;

    private LiveData<List<PostContact>> contacts;

    public ContactsViewModel (){
        repository = new PostContactsRepository();
        //contacts = repository.getContacts();
        contacts = repository.getAll();
    }

   // public LiveData<List<PostContact>> get(){return contacts;}
    public LiveData<List<PostContact>> get() {return  contacts;}

    public  void add(PostContact contact){ repository.add(contact);}

}
