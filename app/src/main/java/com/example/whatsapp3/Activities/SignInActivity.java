package com.example.whatsapp3.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp3.R;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.UserApi;

public class SignInActivity extends AppCompatActivity {
       // private TextView errorMessage;
//    private String errorMessage;
    private UserApi userApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        EditText username = findViewById(R.id.editTextRegisterPersonName);
        EditText password = findViewById(R.id.editTextPassword);
        EditText confirmPassword = findViewById(R.id.editTextConfirmPassword);
        EditText nickname = findViewById(R.id.editTextUserNickname);
        TextView errorMessage = findViewById(R.id.SignUpErrorMessage);
        userApi = new UserApi();
        Button doneBtn = findViewById(R.id.DoneBtn);
        doneBtn.setOnClickListener(view -> {

            if(!is_Valid_Password(password.getText().toString())){
                errorMessage.setText( "1. A password must be at least 8 characters.\n " +
                                      "2. A password consists of only letters and digits.\n " +
                                      "3. A password must contain at least two digits. \n ");
            }else if(!is_Valid_Username(username.getText().toString())){
                errorMessage.setText( "1. A Username must be at least 5 characters.\n" +
                                      "2. A Username consists of only letters and digits.\n" );
            }
            else if(nickname.getText().toString().length() < 3){
                errorMessage.setText( "A nickname must be at least 3 characters.");
            }else if (password.getText().toString() != confirmPassword.getText().toString()) {
                errorMessage.setText("The passwords don't match\n");
            }else{
                //if(username doesent exist...){
                userApi.post(new User(username.getText().toString(), password.getText().toString(), nickname.getText().toString()));
                Intent i = new Intent(this, ChatActivity.class);
                startActivity(i);
                //}
            }

        });
    }


    public static boolean is_Valid_Username(String username){
        if (username.length() < 5) return false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < username.length(); i++) {

            char ch = username.charAt(i);

            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch)) charCount++;
            else return false;
        }
        return true;
    }

    public static boolean is_Valid_Password(String password) {

        if (password.length() < 8) return false;

        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch)) charCount++;
            else return false;
        }
        return (charCount >= 2 && numCount >= 2);

    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }


    public static boolean is_Numeric(char ch) {

        return (ch >= '0' && ch <= '9');
    }
}