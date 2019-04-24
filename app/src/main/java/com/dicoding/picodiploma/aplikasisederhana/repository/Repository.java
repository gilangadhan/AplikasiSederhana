package com.dicoding.picodiploma.aplikasisederhana.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;

import com.dicoding.picodiploma.aplikasisederhana.model.Word;
import com.dicoding.picodiploma.aplikasisederhana.model.WordDao;
import com.dicoding.picodiploma.aplikasisederhana.model.WordRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private WordDao wordDao;

    private ExecutorService executorService;

    public Repository(Application application) {
        executorService = Executors.newSingleThreadExecutor();

        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        this.wordDao = db.wordDao();
    }

    public DataSource.Factory<Integer, Word> getList(){
        return wordDao.getList();
    }

    public void insert(final Word word){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.insertWord(word);
            }
        });
    }

    public void delete(final Word word){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.deleteWord(word);
            }
        });
    }
}
