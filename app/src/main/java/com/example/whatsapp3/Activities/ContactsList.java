package com.example.whatsapp3.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp3.Adapters.ContactsListAdapter;
import com.example.whatsapp3.ContactsViewModel;
import com.example.whatsapp3.R;

public class ContactsList extends AppCompatActivity {

    private ContactsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);


        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        

        RecyclerView contactsList = findViewById(R.id.contactsList);
        final ContactsListAdapter adapter = new ContactsListAdapter(this);
        contactsList.setAdapter(adapter);
        contactsList.setLayoutManager(new LinearLayoutManager(this));

        
        viewModel.get().observe(this, postContacts -> {
            adapter.setPosts(postContacts);
        });

        //adapter.setPosts(viewModel.get());
       /* List<PostContact> contacts = new ArrayList<>();
        contacts.add(new PostContact("Yarin", "yerin", "hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        contacts.add(new PostContact("Yarin", "yerin","hi!", "13:15"));
        contacts.add(new PostContact("Avital", "vita","hi!", "13:15"));
        adapter.setPosts(contacts);
*/
    }
}