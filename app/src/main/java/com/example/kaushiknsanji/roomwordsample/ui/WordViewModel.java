package com.example.kaushiknsanji.roomwordsample.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.kaushiknsanji.roomwordsample.data.WordRepository;
import com.example.kaushiknsanji.roomwordsample.data.local.Word;

import java.util.List;

/**
 * ViewModel of the app that keeps a reference to the Word Repository {@link WordRepository}
 * and an up to date list of all {@link Word}s.
 *
 * @author Kaushik N Sanji
 */
public class WordViewModel extends AndroidViewModel {

    private LiveData<List<Word>> mAllWords;
    private WordRepository mWordRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = WordRepository.getInstance(application);
        mAllWords = mWordRepository.getAllWords();
    }

    public void insert(Word word) {
        mWordRepository.insert(word);
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }
}
