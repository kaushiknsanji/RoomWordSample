package com.example.kaushiknsanji.roomwordsample.data.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * RoomDatabase class for setting up the database of the App.
 *
 * @author Kaushik N Sanji
 */
@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sWordDatabaseCallback = new Callback() {
        /**
         * Called when the database has been opened.
         *
         * @param db The database.
         */
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            //Populating the database with initial data in a background thread using AsyncTask
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static WordRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class,
                            "word_database")
                            .addCallback(sWordDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao wordDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mWordDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mWordDao = db.wordDao();
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.deleteAll();
            mWordDao.insert(new Word("Hello"));
            mWordDao.insert(new Word("World"));
            return null;
        }
    }

}
