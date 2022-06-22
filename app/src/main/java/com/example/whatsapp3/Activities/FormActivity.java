package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsapp3.AppDataBase;
import com.example.whatsapp3.PostDao;
import com.example.whatsapp3.R;

public class FormActivity extends AppCompatActivity {

    private PostDao postDao;
    private AppDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        db = Room.databaseBuilder(getApplicationContext(), com.example.whatsapp3.AppDataBase.class, "postsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

       /* Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(view -> {
            EditText editContact = findViewById(R.id.editContact);
            Post post = new Post(0,editContact.getText().toString());
            postDao.insert(post);
            finish();
        });*/

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(view -> {


            Intent intent = new Intent(this, ContactsList.class);
            intent.putExtra("lastActivity", "FormActivity");
            EditText nickName =  (EditText) findViewById(R.id.searchNickname);
            EditText userName =  (EditText) findViewById(R.id.searchUserName);
            EditText server =  (EditText) findViewById(R.id.searchServer);

            //userApi.checkRegister(user, userName.getText().toString())

            intent.putExtra("nickName", nickName.getText().toString());
            intent.putExtra("id", userName.getText().toString());
            intent.putExtra("server", server.getText().toString());
//            intent.putExtra("contact", postContact);
            startActivity(intent);
        });
    }
}