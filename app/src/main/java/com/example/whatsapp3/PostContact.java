package com.example.whatsapp3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostContact {

    @PrimaryKey
    private String id;
    private String name;
    private String message;
    private String lastdate;
    //private Image profilePic;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getlastdate() {
        return lastdate;
    }

    public void setlastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostContact(String id, String name,String message, String lastdate ) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.lastdate = lastdate;
    }
}
