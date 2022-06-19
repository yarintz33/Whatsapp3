package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.whatsapp3.Adapters.ContactsListAdapter;
import com.example.whatsapp3.AppDataBase;
import com.example.whatsapp3.ContactsViewModel;
import com.example.whatsapp3.PostContact;
import com.example.whatsapp3.PostDao;
import com.example.whatsapp3.R;

public class ContactsList extends AppCompatActivity implements contactsClickListener{
    private int flag = 0;
    private PostDao postDao;
    private AppDataBase db;
    private ContactsViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        db = Room.databaseBuilder(getApplicationContext(), com.example.whatsapp3.AppDataBase.class, "postsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);


        RecyclerView contactsList = findViewById(R.id.contactsList);
        final ContactsListAdapter adapter = new ContactsListAdapter(this, this);
        contactsList.setAdapter(adapter);
        contactsList.setLayoutManager(new LinearLayoutManager(this));

        
      viewModel.get().observe(this, postContacts -> {
            adapter.setPosts(postContacts);
            if(flag == 1){
                PostContact pc = new PostContact("addContact..", "bdika", "hi", "work!");
                viewModel.add(pc);
            }
            flag++;


        });



    }

    @Override
    public void onItemClicked(PostContact postContact) {
//        Toast.makeText(this, postContact.getName(), Toast.LENGTH_SHORT).show();
//        PostContact pc = new PostContact("addContact..", "bdika2", "hi", "work!");
//        viewModel.add(pc);
        Intent j = new Intent(this, ChatActivity.class);
        startActivity(j);
    }
}