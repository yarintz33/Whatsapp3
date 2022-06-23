package com.example.whatsapp3.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.whatsapp3.R;
import com.example.whatsapp3.RealPathUtil;
import com.example.whatsapp3.User;
import com.example.whatsapp3.api.UserApi;
import com.example.whatsapp3.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
       // private TextView errorMessage;
//    private String errorMessage;

ActivitySignInBinding binding;
String path;
    private UserApi userApi;
    private MutableLiveData<User> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    binding = ActivitySignInBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    clickListeners();

        setContentView(R.layout.activity_sign_in);
        user = new MutableLiveData<>();
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
            }else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                errorMessage.setText("The passwords don't match\n");
            }else{
                //if(username doesent exist...){
                userApi.checkRegister(user,username.getText().toString());




                if(user.getValue()==null){





                    userApi.post(new User(username.getText().toString(), password.getText().toString(), nickname.getText().toString()));
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                }else{
                    errorMessage.setText("Username already exist!\n");
                }

                //}
            }

        });
    }

    private void clickListeners() {
        binding.imageButton.setOnClickListener(v->{
            if(ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent();
                intent.setType("inage/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,10);
            }else{
                ActivityCompat.requestPermissions(SignInActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }
        });

        binding.DoneBtn.setOnClickListener(v->{

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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            Context context = SignInActivity.this;
            path = RealPathUtil.getRealPath(context,uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            binding.imageview.setImageBitmap(bitmap);

        }
    }
}