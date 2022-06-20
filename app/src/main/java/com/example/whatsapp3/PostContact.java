package com.example.whatsapp3;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostContact {

    @PrimaryKey @NonNull
    private String id;
    private String name;
    private String last;
    private String lastdate;
    private String server;

    //private Image profilePic;


    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }


    public PostContact(String id, String name, String last, String lastdate, String server) {
        this.id = id;
        this.name = name;
        this.last = last;
        this.lastdate = lastdate;
        this.server = server;
    }
}
