package com.dicoding.picodiploma.aplikasisederhana.model;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface WordDao {

    @Query("Select * from Word")
    public DataSource.Factory<Integer, Word> getList();

    @Insert
    public void insertWord(Word word);

    @Delete
    public void deleteWord(Word word);
}
