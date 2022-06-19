package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp3.R;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.UserApi;

public class SignInActivity extends AppCompatActivity {

    private UserApi userApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        EditText username = findViewById(R.id.editTextRegisterPersonName);
        EditText password = findViewById(R.id.editTextConfirmPassword);
        EditText nickname = findViewById(R.id.editTextUserNickname);
        userApi = new UserApi();
        Button doneBtn = findViewById(R.id.DoneBtn);
        doneBtn.setOnClickListener(view -> {

            //if(username doesent exist...){
            userApi.post(new User(username.getText().toString(), password.getText().toString(), nickname.getText().toString()));
            Intent i = new Intent(this, ChatActivity.class);
            startActivity(i);
            //}
        });
    }
}