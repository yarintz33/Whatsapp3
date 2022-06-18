package com.example.whatsapp3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Post.class, PostContact.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract PostDao postDao();
    public abstract ContactsDao contactsDao();

}