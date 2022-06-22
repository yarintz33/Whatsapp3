package com.example.whatsapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whatsapp3.Activities.ContactsList;

public class Settings extends AppCompatActivity {

    public static String serverNum;
    public static int backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ConstraintLayout background = findViewById(R.id.settingsPage);
        background.setBackgroundColor(Settings.backgroundColor);

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

        Button DarkMode = findViewById(R.id.darkMode);
        DarkMode.setOnClickListener(view -> {
            Settings.backgroundColor = (Color.rgb(64,64,64));

            Intent intent = new Intent(this, ContactsList.class);
            intent.putExtra("lastActivity", "Settings");
            startActivity(intent);
        });
        Button lightMode = findViewById(R.id.lightMode);
        lightMode.setOnClickListener(view -> {
            Settings.backgroundColor = (Color.rgb(234,221,201));

            Intent intent = new Intent(this, ContactsList.class);
            intent.putExtra("lastActivity", "Settings");
            startActivity(intent);
        });

    }

    public static void setServerNum(String serverNum) {
        Settings.serverNum = serverNum;
    }

    public static void setBackgroundColor(int backgroundColor) {
        Settings.backgroundColor = backgroundColor;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ConstraintLayout background = findViewById(R.id.settingsPage);
        background.setBackgroundColor(Settings.backgroundColor);

    }
}