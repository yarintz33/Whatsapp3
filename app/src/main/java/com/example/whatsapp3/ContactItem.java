package com.example.whatsapp3;

public class ContactItem {
    private int profileIMG;
    private String nickName;
    private String lastMessage;

    public ContactItem(int profileIMG, String nickName, String lastMessage) {
        this.profileIMG = profileIMG;
        this.nickName = nickName;
        this.lastMessage = lastMessage;
    }

    public ContactItem(String nickName) {
        this.profileIMG = R.drawable.ic_person;
        this.nickName = nickName;
        this.lastMessage = "";
    }

    public int getProfileIMG() {
        return profileIMG;
    }

    public void setProfileIMG(int profileIMG) {
        this.profileIMG = profileIMG;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
