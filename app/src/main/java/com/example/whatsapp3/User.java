package com.example.whatsapp3;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey @NonNull
    String id;
    String password;
    String name;
    String jwtToken;


    //Image profilePic;

    public User(@NonNull String id,String password,String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public User(@NonNull String id,String password,String name, String jwtToken) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.jwtToken = jwtToken;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
