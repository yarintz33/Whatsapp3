package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.whatsapp3.Adapters.ContactsListAdapter;
import com.example.whatsapp3.AppDataBase;
import com.example.whatsapp3.ChatAndContactsList;
import com.example.whatsapp3.ContactsViewModel;
import com.example.whatsapp3.Message;
import com.example.whatsapp3.PostContact;
import com.example.whatsapp3.PostDao;
import com.example.whatsapp3.R;
import com.example.whatsapp3.Settings;
import com.example.whatsapp3.Token;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.MessageApi;
import com.example.whatsapp3.api.PostContactApi;
import com.example.whatsapp3.api.TokenApi;
import com.example.whatsapp3.api.UserApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.List;


    
    
public class ContactsList extends AppCompatActivity  implements contactsClickListener{
    private MutableLiveData<List<Message>> messages;
    private TokenApi tokenApi;
    private int flag = 0;
    private String token;
    private PostDao postDao;
    private AppDataBase db;
    private ContactsViewModel viewModel;
    private PostContactApi postContactApi;
    private UserApi userApi;
    private MessageApi messageApi;
    private String incomingNickName;
    private String incomingId;
    private String incomingServerNumber;
    private int enter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Intent i = new Intent(this, ChatAndContactsList.class);
            startActivity(i);
        }


        RecyclerView contactsList = findViewById(R.id.contactsList);
        contactsList.setBackgroundColor(Settings.backgroundColor);


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ContactsList.this, instanceIdResult -> {
            token = instanceIdResult.getToken();
            tokenApi = new TokenApi();
            tokenApi.post(new Token("Yarin", instanceIdResult.getToken()));
        });


        messages = new MutableLiveData<>();
        db = Room.databaseBuilder(getApplicationContext(), com.example.whatsapp3.AppDataBase.class, "postsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();
        //viewModel = new ViewModelProvider(this).get(MessageViewModel.calss);
        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);

        PostContact pc = new PostContact("Maayan", "bdika", "", "", "localhost:5286");

        FloatingActionButton saveBtn = findViewById(R.id.addBtn);
        saveBtn.setOnClickListener(view -> {
//             viewModel.add(pc);
            Intent i = new Intent(this, FormActivity.class);
            startActivity(i);
        });
        FloatingActionButton settingsBtn = findViewById(R.id.settings);
        settingsBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, Settings.class);
            startActivity(i);
        });



        messageApi = new MessageApi();
        userApi = new UserApi();
        postContactApi = new PostContactApi();
        final ContactsListAdapter adapter = new ContactsListAdapter(this, this);
        contactsList.setAdapter(adapter);
        contactsList.setLayoutManager(new LinearLayoutManager(this));
        User newUser = new User("Yarin","1234", "yerin" );

        viewModel.get().observe(this, postContacts -> {
            adapter.setPosts(postContacts);
            for(PostContact con :postContacts){
               // Log.d("Info",con.getLast());
            }
            if(flag == 1){
               // viewModel.add(pc);
            }
            flag++;
        });

        Intent mIntent = getIntent();
        String previousActivity= mIntent.getStringExtra("lastActivity");
//        String activity = mIntent.getStringExtra("activity");

        if (previousActivity!= null && previousActivity.equals("FormActivity")){
//            Intent incomingIntent = getIntent();
            incomingNickName = mIntent.getStringExtra("nickName");
            incomingId = mIntent.getStringExtra("id");
            incomingServerNumber = mIntent.getStringExtra("server");
            PostContact contact = new PostContact(incomingId, incomingNickName, "", "", incomingServerNumber);
            viewModel.add(contact);
        }
    //postContactApi.post(pc);
    //userApi.post(newUser);
        //messageApi.get(messages, "Maayan");
        int i = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView contactsList = findViewById(R.id.contactsList);
        contactsList.setBackgroundColor(Settings.backgroundColor);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Intent i = new Intent(this, ChatAndContactsList.class);
            startActivity(i);
        }
    }

    @Override
    public void onItemClicked(PostContact postContact) {
//        Toast.makeText(this, postContact.getName(), Toast.LENGTH_SHORT).show();
//        PostContact pc = new PostContact("addContact..", "bdika2", "hi", "work!");
//        viewModel.add(pc);
        /* Intent j = new Intent(this, ChatActivity.class);
        startActivity(j);*/
        if(viewModel.get().getValue().size() > 0) {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("nickName", postContact.getName());
            intent.putExtra("id", postContact.getId());

        //intent.putExtra("contact", po  stContact);
            startActivity(intent);
        }
    }


}