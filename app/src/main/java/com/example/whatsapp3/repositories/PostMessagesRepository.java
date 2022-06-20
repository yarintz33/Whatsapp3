package com.example.whatsapp3.repositories;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.AppDB;
import com.example.whatsapp3.ContactsDao;
import com.example.whatsapp3.Message;
import com.example.whatsapp3.api.MessageApi;

import java.util.LinkedList;
import java.util.List;

public class PostMessagesRepository extends AppCompatActivity {

    private String contactId;
    private AppDB db;
    private MessageApi messageApi;
    private MessageListData messageListData;
    private ContactsDao dao;
//    private MessageApi api;

    public PostMessagesRepository(String username){
        this.contactId = username;
        /*db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "myDB")
                .allowMainThreadQueries().build();
        dao = db.contactsDao();*/
        messageApi = new MessageApi();
        messageListData = new MessageListData();
        //api = new PostContactApi();


        //dao = db.PostDao;
//        contactsListData = new ContactsListData();
        //api = new PostContactApi(contactsListData, dao);
    }


    class MessageListData extends MutableLiveData<List<Message>> {
        public MessageListData() {
            super();

            List<Message> messages = new LinkedList<>();


            //contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
            //contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
            //contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
            //contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));

            setValue(messages);

        }

        @Override
        protected void onActive() {
            super.onActive();
        /*new Thread(()->{
            contactsListData.postValue(dao.get());
        });*/


            //postContactApi = new PostContactApi();
            messageApi.get(this, contactId);
        }

    }
    public LiveData<List<Message>> getAll() {return messageListData;}

    public void add(Message message, String contactName,boolean addFlag){
        messageApi.add(messageListData, message, contactName, addFlag); // contactId );
    }

}

