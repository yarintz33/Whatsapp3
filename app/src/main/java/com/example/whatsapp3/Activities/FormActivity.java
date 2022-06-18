package com.example.whatsapp3.Activities;

import android.os.Bundle;

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
    }
}