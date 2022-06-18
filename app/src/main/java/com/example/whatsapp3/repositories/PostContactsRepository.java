package com.example.whatsapp3.repositories;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.AppDB;
import com.example.whatsapp3.ContactsDao;
import com.example.whatsapp3.PostContact;
import com.example.whatsapp3.api.PostContactApi;

import java.util.LinkedList;
import java.util.List;

public class PostContactsRepository extends AppCompatActivity {

    private AppDB db;
    private PostContactApi postContactApi;
    private  ContactsListData contactsListData;
    private ContactsDao dao;
    private PostContactApi api;

    public PostContactsRepository(){

        /*db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "myDB")
                .allowMainThreadQueries().build();
        dao = db.contactsDao();*/
        postContactApi = new PostContactApi();
        contactsListData = new ContactsListData();
        //api = new PostContactApi();


        //dao = db.PostDao;
//        contactsListData = new ContactsListData();
        //api = new PostContactApi(contactsListData, dao);
    }


    class ContactsListData extends MutableLiveData<List<PostContact>> {
        public ContactsListData() {
            super();

            List<PostContact> contacts = new LinkedList<>();


        //contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        //contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        //contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        //contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));

            setValue(contacts);

        }

        @Override
        protected void onActive() {
            super.onActive();
        /*new Thread(()->{
            contactsListData.postValue(dao.get());
        });*/


            //postContactApi = new PostContactApi();
            postContactApi.get(this);
        }

    }
    public LiveData<List<PostContact>> getAll() {return contactsListData;}

    public void add(PostContact contact){
        postContactApi.add(contactsListData, contact );
    }

    }

