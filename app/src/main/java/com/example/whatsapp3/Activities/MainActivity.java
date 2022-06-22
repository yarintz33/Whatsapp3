package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.R;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.TokenApi;
import com.example.whatsapp3.api.UserApi;
import com.example.whatsapp3.databinding.ActivityMainBinding;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private String errorMessage = "";
    private MutableLiveData<User> user = null;
    private UserApi userApi;
    private TokenApi tokenApi;
    private String token;

    private ActivityMainBinding MainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());
        user = new MutableLiveData<>();

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
            token = instanceIdResult.getToken();
            //tokenApi = new TokenApi();
           // tokenApi.post(new Token("Yarin", instanceIdResult.getToken()));
        });

        setContentView(MainActivityBinding.getRoot());
        userApi = new UserApi();
        EditText username = findViewById(R.id.editTextLoginPersonName);
        EditText password = findViewById(R.id.editTextLoginPassword);
        TextView TVerrorMessage = findViewById(R.id.loginErrorMessage);
        // Post post = new Post(0,editContact.getText().toString());
        //PostApi postApi = new PostApi();
        //postApi.get();


        MainActivityBinding.loginBtn.setOnClickListener(view -> {

            userApi.signIn(username.getText().toString());
            //wait;
            userApi.get(user, username.getText().toString(), password.getText().toString());
            if(user != null){

                Intent i = new Intent(this, ContactsList.class);
                startActivity(i);
            }
            else{
                TVerrorMessage.setText( "username and/or password are incorrect \n");
            }
        });

        MainActivityBinding.SignInBtn.setOnClickListener(view -> {

            Intent intent = new Intent(this, SignInActivity.class);
            intent.putExtra("lastActivity", "MainActivity");
            startActivity(intent);
        });

        /*FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
            String token = instanceIdResult.getToken();
        });*/
    }




}