package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.R;
import com.example.whatsapp3.Settings;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.TokenApi;
import com.example.whatsapp3.api.UserApi;
import com.example.whatsapp3.databinding.ActivityMainBinding;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private String errorMessage = "";
    private MutableLiveData<User> user = null;
    private UserApi userApi;
    private TokenApi tokenApi;
    private String token;
    public static String logedInUsername;
    private ActivityMainBinding MainActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Settings.setServerNum("http://10.0.2.2:5286/api/");
        Settings.setBackgroundColor(Color.rgb(234,221,201));

        super.onCreate(savedInstanceState);

        MainActivityBinding = ActivityMainBinding.inflate(getLayoutInflater());
        user = new MutableLiveData<>();
        //user.setValue(new User("0", "", ""));
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, instanceIdResult -> {
            token = instanceIdResult.getToken();
            //tokenApi = new TokenApi();
           // tokenApi.post(new Token("Yarin", instanceIdResult.getToken()));
        });

        setContentView(MainActivityBinding.getRoot());
        userApi = new UserApi();
        userApi.post(new User("Yarin","111", "yerin"));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userApi.post(new User("Maayan","111", "satla"));
        EditText username = findViewById(R.id.editTextLoginPersonName);
        EditText password = findViewById(R.id.editTextLoginPassword);

        TextView TVerrorMessage = findViewById(R.id.loginErrorMessage);
        // Post post = new Post(0,editContact.getText().toString());
        //PostApi postApi = new PostApi();
        //postApi.get();
        MutableLiveData<User> logedInUser = user;


        user.observe(this, User -> {
            if(User!= null){
                if (!User.getId().equals("")) {
//                    Log.d("Info", user.getValue().getName());
                    Intent i = new Intent(this, ContactsList.class);
                    logedInUsername = username.getText().toString();
                    startActivity(i);
                } else {
                    TVerrorMessage.setText("username and/or password are incorrect \n");
                }
            }
        });

        MainActivityBinding.loginBtn.setOnClickListener(view -> {
if(user.getValue() == null){
    Log.d("Info","user.getValue());");

}
            if(username.getText().toString().equals("") || password.getText().toString().equals("")){
                TVerrorMessage.setText("please enter username and password\n");
            }else{
                userApi.signIn(user, username.getText().toString(), password.getText().toString());
//                UserApi userApiBdika = new UserApi(user);
//                userApiBdika.get(user, username.getText().toString(), password.getText().toString());
                //wait;
                //userApi.get(user, username.getText().toString(), password.getText().toString());
            }
            /*while(user.getValue() == null) {
            }*/

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