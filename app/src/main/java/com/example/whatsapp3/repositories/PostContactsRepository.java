package com.example.whatsapp3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.PostContact;
import com.example.whatsapp3.PostDao;
import com.example.whatsapp3.api.PostApi;
import com.example.whatsapp3.api.PostContactApi;

import java.util.LinkedList;
import java.util.List;

public class PostContactsRepository {


    private  ContactsListData contactsListData;
    private PostDao dao;
    private PostApi api;

    public PostContactsRepository(){
        //db=...
        //dao = db.PostDao;
        contactsListData = new ContactsListData();
        //api = new PostContactApi(contactsListData, dao);
    }


    class ContactsListData extends MutableLiveData<List<PostContact>> {
        public ContactsListData() {
            super();

            List<PostContact> contacts = new LinkedList<>();


        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));

            setValue(contacts);
            PostContactApi postContactApi = new PostContactApi();
            postContactApi.get(this);
        }

        @Override
        protected void onActive() {
            super.onActive();
        /*new Thread(()->{
            contactsListData.postValue(dao.get());
        });*/
        }
    }
    public LiveData<List<PostContact>> getAll() {return contactsListData;}


}
