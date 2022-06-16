package com.example.whatsapp3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp3.Adapters.ContactsListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        RecyclerView contactsList = findViewById(R.id.contactsList);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        contactsList.setAdapter(adapter);
        contactsList.setLayoutManager(new LinearLayoutManager(this));

        List<PostContact> contacts = new ArrayList<>();
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        contacts.add(new PostContact("Yarin", "yerin"));
        contacts.add(new PostContact("Avital", "vita"));
        adapter.setPosts(contacts);

    }
}