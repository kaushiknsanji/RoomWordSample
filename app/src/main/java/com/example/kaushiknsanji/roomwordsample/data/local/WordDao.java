package com.example.kaushiknsanji.roomwordsample.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Room Database Access for Room Entity {@link Word}
 * that provides API for reading and writing data.
 *
 * @author Kaushik N Sanji
 */
@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE from word_table")
    void deleteAll();

    @Query("Select * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
}
