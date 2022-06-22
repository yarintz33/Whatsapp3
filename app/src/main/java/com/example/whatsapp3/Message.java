package com.example.whatsapp3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String content;
    private String created;
    boolean sent;
    private String from;
    private String to;
    public Message(String content, boolean sent) {

        this.content = content;
        //this.created = created;
        this.sent = sent;

    }
    public Message(String content){
        this.content = content;
    }
    public int getId() {
        return id;
    }

    public Message(String content, String from, String to) {
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public Message(String content, boolean sent, String from, String to) {
        this.content = content;
        this.sent = sent;
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}