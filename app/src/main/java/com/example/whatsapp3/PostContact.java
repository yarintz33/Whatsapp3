package com.example.whatsapp3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PostContact {

    @PrimaryKey
    private String id;
    private String name;

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

    public PostContact(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
