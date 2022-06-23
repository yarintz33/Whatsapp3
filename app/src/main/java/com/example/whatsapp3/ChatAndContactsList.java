package com.example.whatsapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whatsapp3.Activities.ChatActivity;
import com.example.whatsapp3.Activities.ContactsList;
import com.example.whatsapp3.Activities.FormActivity;
import com.example.whatsapp3.Activities.MainActivity;
import com.example.whatsapp3.Activities.contactsClickListener;
import com.example.whatsapp3.Adapters.ContactsListAdapter;
import com.example.whatsapp3.Adapters.MessagesListAdapter;
import com.example.whatsapp3.api.MessageApi;
import com.example.whatsapp3.api.PostContactApi;
import com.example.whatsapp3.api.TokenApi;
import com.example.whatsapp3.api.UserApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChatAndContactsList extends AppCompatActivity implements contactsClickListener {
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
    private MessagesViewModel messagesViewModel;
    private EditText textBar;
    private List<Post> posts;
    private ArrayAdapter<Post> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_and_contacts_list);


        //list:

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Intent i = new Intent(this, ContactsList.class);
            startActivity(i);
        }


        RecyclerView contactsList = findViewById(R.id.contactsList);
        LinearLayout background = findViewById(R.id.chatAndListPage);
        background.setBackgroundColor(Settings.backgroundColor);


        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ChatAndContactsList.this, instanceIdResult -> {
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
        LinearLayout background = findViewById(R.id.chatAndListPage);
        background.setBackgroundColor(Settings.backgroundColor);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Intent i = new Intent(this, ContactsList.class);
            startActivity(i);
        }
    }

    public void sendMessage(String message)
    {
        Log.d("Info", incomingId);
        Log.d("Info", message);
        Date date = (Date) Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
//        messagesViewModel.add(new Message(0,message, strDate,true),incomingId, true);
        messagesViewModel.add(new Message(message,true, MainActivity.logedInUsername ,incomingId ),incomingId, true);

    }

    @Override
    public void onItemClicked(PostContact postContact) {
//        Toast.makeText(this, postContact.getName(), Toast.LENGTH_SHORT).show();
//        PostContact pc = new PostContact("addContact..", "bdika2", "hi", "work!");
//        viewModel.add(pc);
        /* Intent j = new Intent(this, ChatActivity.class);
        startActivity(j);*/
//        if(viewModel.get().getValue().size() > 0) {
//            Intent intent = new Intent(this, ChatActivity.class);
//            intent.putExtra("nickName", postContact.getName());
//            intent.putExtra("id", postContact.getId());
//
//            //intent.putExtra("contact", po  stContact);
//            startActivity(intent);
//        }
        incomingNickName = postContact.getName();
        incomingId = postContact.getId();
        //chat:
        TextView userNickname = (TextView)findViewById(R.id.NcontactItemNickname);
        messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        messagesViewModel.setContatId(incomingId);
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "postsDB")
                .allowMainThreadQueries().build();

        MyService2.messages = messagesViewModel;

        postDao = db.postDao();

        EditText editText = (EditText) findViewById(R.id.writeMessage);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    sendMessage(v.getText().toString());
                    handled = true;
                    editText.setText("");
                }
                return handled;
            }
        });

        RecyclerView messagesList = findViewById(R.id.messagesList);

        final MessagesListAdapter chatAdapter = new MessagesListAdapter(this); ///// ,this)
        messagesList.setAdapter(chatAdapter);
        messagesList.setLayoutManager(new LinearLayoutManager(this));

        messagesViewModel.get().observe(this, messages -> {
            chatAdapter.setPosts(messages);
            //MyService2.messages = messagesViewModel;

        });

        userNickname.setText(incomingNickName);
    }
}
