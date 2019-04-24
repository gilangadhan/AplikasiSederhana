package com.dicoding.picodiploma.aplikasisederhana.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.dicoding.picodiploma.aplikasisederhana.model.Word;
import com.dicoding.picodiploma.aplikasisederhana.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository= new Repository(application);

        List<Word> list= populateWord();
        for (int i = 0; i < list.size(); i++) {
            //repository.insert(list.get(i));
        }
    }

    public LiveData<PagedList<Word>> getWord(){
        return new LivePagedListBuilder<>(
                repository.getList(), 1).build();

    }

    private List<Word> populateWord(){
        List<Word> list = new ArrayList<>();

        list.add(new Word("Kamu"));
        list.add(new Word("Bukan"));
        list.add(new Word("Android"));
        list.add(new Word("Dev"));

        return list;
    }

    public void delete(Word word) {
        repository.delete(word);
    }

    public void insert(Word word){
        repository.insert(word);
    }
}

