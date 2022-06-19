package com.example.whatsapp3.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.whatsapp3.Adapters.ContactsListAdapter;
import com.example.whatsapp3.AppDataBase;
import com.example.whatsapp3.ContactsViewModel;
import com.example.whatsapp3.Message;
import com.example.whatsapp3.PostContact;
import com.example.whatsapp3.PostDao;
import com.example.whatsapp3.R;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.MessageApi;
import com.example.whatsapp3.api.PostContactApi;
import com.example.whatsapp3.api.UserApi;

import java.util.List;


    
    
public class ContactsList extends AppCompatActivity { /// implements contactsClickListener
    private MutableLiveData<List<Message>> messages;
    private int flag = 0;
    private PostDao postDao;
    private AppDataBase db;
    private ContactsViewModel viewModel;
    private PostContactApi postContactApi;
    private UserApi userApi;
    private MessageApi messageApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        db = Room.databaseBuilder(getApplicationContext(), com.example.whatsapp3.AppDataBase.class, "postsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);


        messageApi = new MessageApi();
        userApi = new UserApi();
        postContactApi = new PostContactApi();
        RecyclerView contactsList = findViewById(R.id.contactsList);
        final ContactsListAdapter adapter = new ContactsListAdapter(this); ///// ,this)
        contactsList.setAdapter(adapter);
        contactsList.setLayoutManager(new LinearLayoutManager(this));
        PostContact pc = new PostContact("addContact..", "bdika", "hi", "work!", "localhost:5286");
        User newUser = new User("Yarin","1234", "yerin" );

        viewModel.get().observe(this, postContacts -> {
            adapter.setPosts(postContacts);
            if(flag == 1){
                viewModel.add(pc);
            }
            flag++;


        });
    //postContactApi.post(pc);
    //userApi.post(newUser);
        messageApi.get(messages, "Maayan");
        int i = 0;


    }

  /*  @Override
    public void onItemClicked(PostContact postContact) {
//        Toast.makeText(this, postContact.getName(), Toast.LENGTH_SHORT).show();
//        PostContact pc = new PostContact("addContact..", "bdika2", "hi", "work!");
//        viewModel.add(pc);
        Intent j = new Intent(this, ChatActivity.class);
        startActivity(j);
    }  */
}