package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.whatsapp3.Adapters.MessagesListAdapter;
import com.example.whatsapp3.AppDataBase;
import com.example.whatsapp3.Message;
import com.example.whatsapp3.MessagesViewModel;
import com.example.whatsapp3.Post;
import com.example.whatsapp3.PostDao;
import com.example.whatsapp3.R;
import com.example.whatsapp3.SampleViewModel;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private MessagesViewModel messagesViewModel;
    private PostDao postDao;
    private AppDataBase db;
    private List<Post> posts;
    private ArrayAdapter<Post> adapter;
    private SampleViewModel viewModel;
    private String incomingNickName;
    private String incomingId;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView userNickname = (TextView)findViewById(R.id.contactItemNickname);

        String username = "Maayan";


        messagesViewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        messagesViewModel.setContatId(username);
        db = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "postsDB")
                .allowMainThreadQueries().build();

        postDao = db.postDao();

        EditText editText = (EditText) findViewById(R.id.writeMessage);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    sendMessage(v.getText().toString());
                    handled = true;
                }
                return handled;
            }
        });







        RecyclerView messagesList = findViewById(R.id.messagesList);
        final MessagesListAdapter adapter = new MessagesListAdapter(this); ///// ,this)
        messagesList.setAdapter(adapter);
        messagesList.setLayoutManager(new LinearLayoutManager(this));

        messagesViewModel.get().observe(this, messages -> {
                    adapter.setPosts(messages);

                });
        //handlePosts();
//        FloatingActionButton addBtn = findViewById(R.id.addBtn);
   //     viewModel = new ViewModelProvider(this).get(SampleViewModel.class);
        // foo is the updated string.. we set the string as the title (on top of the activity page)
//        viewModel.getDate().observe(this, date -> getSupportActionBar().setTitle(date));

        //FloatingActionButton btnAdd = findViewById(R.id.addBtn);
//        addBtn.setOnClickListener(view -> {
//            Intent i = new Intent(this, FormActivity.class);
//            startActivity(i);
//            String currentDateTime = DateFormat.getDateTimeInstance().format(new Date());
//            viewModel.getDate().setValue(currentDateTime);
//        });


//        posts = new ArrayList<>();
//        ListView lvPost = findViewById(R.id.lvPosts);
//        adapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1,posts);
//        lvPost.setAdapter(adapter);
//        lvPost.setOnItemLongClickListener((adapterView, view, i, l) -> {
//
//             Post post = posts.remove(i);
//             postDao.delete(post);
//             adapter.notifyDataSetChanged();
//             return true;
// });
        /*lvPost.setOnItemClickListener(view -> {

        });*/

        Intent incomingIntent = getIntent();
        incomingNickName = incomingIntent.getStringExtra("nickName");

        incomingId = incomingIntent.getStringExtra("id");

        userNickname.setText(incomingNickName);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        posts.clear();
//        posts.addAll(postDao.index());
//        adapter.notifyDataSetChanged();

    }
    public void sendMessage(String message)
    {
        Log.d("Info", incomingId);
        //Log.d("Info", message);
        //Date date = (Date) Calendar.getInstance().getTime();
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        //String strDate = dateFormat.format(date);
        messagesViewModel.add(new Message(message),incomingId);
    }
}


