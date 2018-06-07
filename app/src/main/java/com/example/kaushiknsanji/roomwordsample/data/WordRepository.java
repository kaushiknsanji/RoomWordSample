package com.example.kaushiknsanji.roomwordsample.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.kaushiknsanji.roomwordsample.data.local.Word;
import com.example.kaushiknsanji.roomwordsample.data.local.WordDao;
import com.example.kaushiknsanji.roomwordsample.data.local.WordRoomDatabase;

import java.util.List;

/**
 * Class that follows the Repository pattern for providing access to
 * the data of the App.
 *
 * @author Kaushik N Sanji
 */
public class WordRepository {

    private static WordRepository INSTANCE;
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    private WordRepository() {
    }

    private WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getInstance(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public static WordRepository getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (WordRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WordRepository(application);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new InsertAsyncTask(mWordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private final WordDao mAsyncWordDao;

        InsertAsyncTask(WordDao wordDao) {
            mAsyncWordDao = wordDao;
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param words The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncWordDao.insert(words[0]);
            return null;
        }
    }
}
