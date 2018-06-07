package com.example.kaushiknsanji.roomwordsample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.kaushiknsanji.roomwordsample.R;
import com.example.kaushiknsanji.roomwordsample.data.local.Word;

import java.util.List;

/**
 * The MainActivity of the App that displays the list of words added
 * to the database using a RecyclerView.
 *
 * @author Kaushik N Sanji
 */
public class MainActivity extends AppCompatActivity {

    private static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private WordViewModel mWordViewModel;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewWordActivity();
            }
        });

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        setupRecyclerView();

        subscribeWords();

    }

    private void startNewWordActivity() {
        Intent intent = new Intent(this, NewWordActivity.class);
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    }

    private void subscribeWords() {
        final WordListAdapter adapter = (WordListAdapter) mRecyclerView.getAdapter();
        Observer<List<Word>> wordListObserver = new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        };

        mWordViewModel.getAllWords().observe(this, wordListObserver);
    }

    private void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        final WordListAdapter wordListAdapter = new WordListAdapter();
        mRecyclerView.setAdapter(wordListAdapter);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
