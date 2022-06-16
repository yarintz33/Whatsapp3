package com.example.whatsapp3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class FormActivity extends AppCompatActivity {

    private com.example.whatsapp3.PostDao postDao;
    private com.example.whatsapp3.AppDataBase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        db = Room.databaseBuilder(getApplicationContext(), com.example.whatsapp3.AppDataBase.class, "postsDB")
                .allowMainThreadQueries().build();
        postDao = db.postDao();

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(view -> {
            EditText editContact = findViewById(R.id.editContact);
            com.example.whatsapp3.Post post = new com.example.whatsapp3.Post(0,editContact.getText().toString());
            postDao.insert(post);
            finish();
        });
    }
}