package com.dicoding.picodiploma.aplikasisederhana.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Word")
public class Word {

    @PrimaryKey
    @ColumnInfo(name = "word")
    @NonNull
    public String word;

    public Word(String word) {
        this.word = word;
    }
}
