package com.dicoding.picodiploma.aplikasisederhana.ui.main;

import android.annotation.SuppressLint;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dicoding.picodiploma.aplikasisederhana.R;
import com.dicoding.picodiploma.aplikasisederhana.model.Word;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WordAdapter extends PagedListAdapter<Word, WordAdapter.WordViewHolder> {

    WordAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new WordViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_word, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {
        final Word word = getItem(i);
        if (word!=null){
            wordViewHolder.textView.setText(word.word);
        }
    }

    @Nullable
    public Word getItemById(int swipedPosition) {
        return getItem(swipedPosition);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    private static DiffUtil.ItemCallback<Word> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Word>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Word oldBookmark, Word newBookmark) {
                    return oldBookmark.word.equals(newBookmark.word);
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Word oldBookmark,@NonNull Word newBookmark) {
                    return oldBookmark.equals(newBookmark);
                }
            };
}
