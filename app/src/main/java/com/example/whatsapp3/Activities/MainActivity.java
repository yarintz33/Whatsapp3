package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.R;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.UserApi;
import com.example.whatsapp3.databinding.ActivityMainBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<User> user;
    private UserApi userApi;
    private ActivityMainBinding MainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(MainActivityBinding.getRoot());
        userApi = new UserApi();
        EditText username = findViewById(R.id.editTextLoginPersonName);
        EditText password = findViewById(R.id.editTextLoginPassword);
        // Post post = new Post(0,editContact.getText().toString());
        //PostApi postApi = new PostApi();
        //postApi.get();


        MainActivityBinding.loginBtn.setOnClickListener(view -> {
            //userApi.get(user, username.getText().toString(), password.getText().toString());
            //if(user != null){
                Intent i = new Intent(this, ContactsList.class);
                startActivity(i);
           // }
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