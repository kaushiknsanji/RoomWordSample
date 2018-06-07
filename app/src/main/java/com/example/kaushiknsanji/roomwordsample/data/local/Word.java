package com.example.kaushiknsanji.roomwordsample.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Room Entity Class for Words
 *
 * @author Kaushik N Sanji
 */
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {
        mWord = word;
    }

    @NonNull
    public String getWord() {
        return mWord;
    }
}
