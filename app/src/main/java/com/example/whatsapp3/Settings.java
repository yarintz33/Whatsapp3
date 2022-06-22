package com.example.whatsapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.whatsapp3.Activities.ContactsList;

public class Settings extends AppCompatActivity {

    public static String serverNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button saveServerNumBtn = findViewById(R.id.saveServerNum);
        saveServerNumBtn.setOnClickListener(view -> {
            EditText serverNum =  (EditText) findViewById(R.id.newServerNum);

            if (serverNum.getText().toString().startsWith("http")){
                Intent intent = new Intent(this, ContactsList.class);
                intent.putExtra("lastActivity", "Settings");
                Settings.serverNum = serverNum.getText().toString();

//            intent.putExtra("server", server.getText().toString());
                startActivity(intent);
            } else {
                TextView TVerrorMessage = findViewById(R.id.serverErrorMessage);
                TVerrorMessage.setText("server number is incorrect \n");
            }
        });
    }

    public static void setServerNum(String serverNum) {
        Settings.serverNum = serverNum;
    }
}