package com.example.whatsapp3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactsDao {

    @Query("SELECT * FROM post")
    List<PostContact> index();

    @Query("SELECT * FROM post WHERE id = :id")
    PostContact get(String id);

    @Insert
    void insert(PostContact... contacts);
    @Update
    void update(PostContact... contacts);
    @Delete
    void delete(PostContact... contacts);
}
