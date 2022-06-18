package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp3.databinding.ActivityMainBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding MainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(MainActivityBinding.getRoot());

        //PostApi postApi = new PostApi();
        //postApi.get();


        MainActivityBinding.loginBtn.setOnClickListener(view -> {
            Intent i = new Intent(this, ContactsList.class);
            startActivity(i);
        });

        MainActivityBinding.SignInBtn.setOnClickListener(view -> {
            Intent j = new Intent(this, SignInActivity.class);
            startActivity(j);
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
            String token = instanceIdResult.getToken();
        });
    }
}